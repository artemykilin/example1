package com.artemy.example.data.local

import com.artemy.example.data.remote.PixabayImageData

class ImageDataMapper(private val queryId: Int, private val pageNum:Int) {
	fun map(image: PixabayImageData): ImageData =
		ImageData(
			image.id,
			image.tags,
			image.user,
			image.likes,
			image.downloads,
			image.comments,
			image.previewUrl,
			image.imageUrl,
			queryId,
			pageNum
		)
}