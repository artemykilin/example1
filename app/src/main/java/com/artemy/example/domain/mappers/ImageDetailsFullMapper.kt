package com.artemy.example.domain.mappers

import com.artemy.example.app.ui.UiImageDetails
import com.artemy.example.domain.entities.ImageDetailsModel
import javax.inject.Inject

class ImageDetailsFullMapper @Inject constructor() {
	fun map(obj: ImageDetailsModel) =
		UiImageDetails(obj.imageUrl, obj.userName, obj.tags, obj.likes, obj.downloads, obj.comments)
}