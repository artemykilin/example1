package com.artemy.example.app.ui

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.artemy.payback.BuildConfig
import com.artemy.example.data.PAGE_LEN
import com.artemy.example.domain.mappers.ImageDetailsShortMapper
import com.artemy.example.domain.usecases.GetPagingDataSourceUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ImageListFragmentViewModel @Inject constructor(
	private val getPagingDataSourceUseCase: GetPagingDataSourceUseCase,
	private val imageDetailsMapper: ImageDetailsShortMapper
) : ViewModel() {

	val requestText = ObservableField(BuildConfig.STARTUP_QUERY_TEXT)

	fun getPagingDataFlow(): Flow<PagingData<UiImageDetailsShort>> =
		Pager(PagingConfig(pageSize= PAGE_LEN, prefetchDistance = 5))
		{ getPagingDataSourceUseCase.get(requestText.get()!!)	}
			.flow
			.cachedIn(viewModelScope)
			.map { pagingData ->
				pagingData.map { input ->
					imageDetailsMapper.fromImageDetailsModel(input)
				}
			}
}
