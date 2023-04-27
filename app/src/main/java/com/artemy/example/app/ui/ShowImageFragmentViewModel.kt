package com.artemy.example.app.ui

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemy.example.domain.mappers.ImageDetailsFullMapper
import com.artemy.example.domain.usecases.GetImageDataUseCase
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.launch
import javax.inject.Inject

class ShowImageFragmentViewModel @Inject constructor(
	private val getImageDataUseCase: GetImageDataUseCase,
	private val imageDetailsMapper: ImageDetailsFullMapper
	): ViewModel() {

	val userNameField = ObservableField<String>()
	val tagsField = ObservableField<String>()
	val likesField = ObservableInt()
	val downloadsField = ObservableInt()
	val commentsField = ObservableInt()

	val hasError = ObservableBoolean(false)

	private val mImageUrl = MutableLiveData<String> ()
	val imageUrlData: LiveData<String> get() = mImageUrl


	fun loadImageDetails(imageId: Int) {
		hasError.set(false)
		viewModelScope.launch (CoroutineName("loadImageDetails")) {
			try {
				with (imageDetailsMapper.map(getImageDataUseCase.get(imageId))) {
					userNameField.set(userName)
					tagsField.set(tags)
					likesField.set(likes)
					downloadsField.set(downloads)
					commentsField.set(comments)
					mImageUrl.postValue(url)
				}
			} catch (e: Exception) {
				hasError.set(true)
			}
		}
	}
}