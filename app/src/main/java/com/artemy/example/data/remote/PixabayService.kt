package com.artemy.example.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayService {
	@GET("api")
	suspend fun loadImages(
		@Query("key") key: String,
		@Query("q") queryText: String,
		@Query("page") page: Int,
		@Query("image_type") imageType: String = "photo"
	): PixabayResponse
}