package com.artemy.example.domain.entities

data class ImageDetailsModel(
	val imageUrl: String,
	val userName: String,
	val tags: String,
	val likes: Int,
	val downloads: Int,
	val comments: Int
)
