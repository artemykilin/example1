package com.artemy.example.domain.usecases

import com.artemy.example.domain.IRepository
import com.artemy.example.domain.entities.ImageDetailsModel
import javax.inject.Inject

class GetImageDataUseCase @Inject constructor(private val repository: IRepository) {
	suspend fun get(imageId: Int): ImageDetailsModel {
		return repository.getImageDetails(imageId)
	}
}