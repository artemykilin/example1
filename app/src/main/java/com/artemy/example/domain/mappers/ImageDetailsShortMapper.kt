package com.artemy.example.domain.mappers

import com.artemy.example.app.ui.UiImageDetailsShort
import com.artemy.example.domain.entities.ImageDetailsShortModel
import javax.inject.Inject

class ImageDetailsShortMapper @Inject constructor() {
	fun fromImageDetailsModel(obj: ImageDetailsShortModel) =
		UiImageDetailsShort(obj.imageId,	obj.userName,	obj.tags,	obj.previewUrl)
}