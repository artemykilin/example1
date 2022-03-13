package com.artemy.example.data.remote

import com.google.gson.annotations.SerializedName

data class PixabayResponse(
	var totalHits: Int = 0,
	var hits: List<PixabayImageData>
)

data class PixabayImageData (
	var id: Int,
	var tags: String,
	var user: String,
	var likes: Int,
	var downloads: Int,
	var comments: Int,

	@SerializedName("previewURL")
	var previewUrl: String,

	@SerializedName("largeImageURL")
	var imageUrl: String
)