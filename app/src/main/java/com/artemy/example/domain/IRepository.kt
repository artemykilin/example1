package com.artemy.example.domain

import androidx.paging.PagingSource
import com.artemy.example.domain.entities.ImageDetailsModel
import com.artemy.example.domain.entities.ImageDetailsShortModel

interface IRepository {
	fun getPagingImageDataSource(queryText: String): PagingSource<Int, ImageDetailsShortModel>
	suspend fun getImageDetails(imageId: Int): ImageDetailsModel
}