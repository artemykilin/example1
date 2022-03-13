package com.artemy.example.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.artemy.payback.BuildConfig
import com.artemy.example.data.local.AppDatabase
import com.artemy.example.data.local.ImageDataMapper
import com.artemy.example.data.local.RemoteQuery
import com.artemy.example.data.remote.PixabayService
import com.artemy.example.domain.entities.ImageDetailsShortModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

const val PAGE_LEN = 20

class PagingImageListSource constructor (
	private val queryText: String,
	private val db: AppDatabase,
	private val pixabayService: PixabayService
): PagingSource<Int, ImageDetailsShortModel>() {

	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageDetailsShortModel> {
		val pageNum = params.key ?: 1
		return try {
			withContext(Dispatchers.IO) {
				if (db.getRemoteQueryDAO().noRequest(queryText)) {
					db.getRemoteQueryDAO().insert(
						RemoteQuery(queryText, Date(System.currentTimeMillis()), -1, 0)
					)
				}

				val remoteQuery = db.getRemoteQueryDAO().getQuery(queryText)
				if (remoteQuery.needsLoadPage(pageNum)) {
					val response = pixabayService.loadImages(BuildConfig.API_KEY, queryText, pageNum)

					val imageList = response.hits.map{
						ImageDataMapper(remoteQuery.id, pageNum).map(it)
					}
					db.getImageDataDAO().insertAll(imageList)
					with(remoteQuery) {
						totalPages = if (response.totalHits == 0) 0 else (response.totalHits / PAGE_LEN) + 1
						loadedPages = pageNum
						db.getRemoteQueryDAO().update(this)
					}
				}

				val nextPage = if (remoteQuery.isLast(pageNum)) null else pageNum.plus(1)
				LoadResult.Page(
					db.getImageDataDAO().loadPage(remoteQuery.id, pageNum),
					null,
					nextPage
				)
			}
		}
		catch (e: Exception) {
			return LoadResult.Error(e)
		}
	}

	override fun getRefreshKey(state: PagingState<Int, ImageDetailsShortModel>): Int? {
		return state.anchorPosition?.let { anchorPosition ->
			val anchorPage = state.closestPageToPosition(anchorPosition)
			anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
		}
	}
}