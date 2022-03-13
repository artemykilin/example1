package com.artemy.example.domain.usecases

import androidx.paging.PagingSource
import com.artemy.example.domain.IRepository
import com.artemy.example.domain.entities.ImageDetailsShortModel
import javax.inject.Inject

class GetPagingDataSourceUseCase @Inject constructor(private val repository: IRepository) {
	fun get(queryText: String): PagingSource<Int, ImageDetailsShortModel> {
		return repository.getPagingImageDataSource(queryText)
	}
}