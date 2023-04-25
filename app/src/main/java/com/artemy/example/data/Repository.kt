package com.artemy.example.data

import android.annotation.SuppressLint
import android.util.Log
import androidx.paging.PagingSource
import com.artemy.example.app.IAppDispatchers
import com.artemy.example.data.local.AppDatabase
import com.artemy.example.data.remote.PixabayService
import com.artemy.example.domain.IRepository
import com.artemy.example.domain.entities.ImageDetailsModel
import com.artemy.example.domain.entities.ImageDetailsShortModel
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@SuppressLint("CheckResult")
class Repository @Inject constructor(
	private val appDispatchers: IAppDispatchers,
	private val appDatabase: AppDatabase,
	private val pixabayService: PixabayService
): IRepository {

	init {
		Observable
			.interval(0, 1, TimeUnit.HOURS)
			.observeOn(Schedulers.io())
			.subscribe(
				{
					appDatabase.getRemoteQueryDAO().deleteOutdatedQueries()
				},
				{ Log.e("Repository", it.localizedMessage, it)}
			)
	}

	override fun getPagingImageDataSource(queryText: String): PagingSource<Int, ImageDetailsShortModel> {
		return PagingImageListSource(normalizeQuery(queryText), appDatabase, pixabayService, appDispatchers.io)
	}

	override suspend fun getImageDetails(imageId: Int): ImageDetailsModel {
		return withContext(appDispatchers.io) {
			appDatabase.getImageDataDAO().loadImageDetails(imageId)
		}
	}

	private fun normalizeQuery(queryText: String): String =
		queryText.trim().replace(Regex("\\s+"), "+")
}