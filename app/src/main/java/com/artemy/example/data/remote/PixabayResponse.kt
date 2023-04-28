package com.artemy.example.data.remote

import com.google.gson.annotations.SerializedName

data class PixabayResponse(
	val totalHits: Int = 0,
	val hits: List<PixabayImageData>
)

data class PixabayImageData (
	val id: Int,
	val tags: String,
	val user: String,
	val likes: Int,
	val downloads: Int,
	val comments: Int,

	@SerializedName("previewURL")
	val previewUrl: String,

	@SerializedName("largeImageURL")
	val imageUrl: String
)