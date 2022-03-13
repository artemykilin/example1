package com.artemy.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.artemy.example.domain.entities.ImageDetailsModel
import com.artemy.example.domain.entities.ImageDetailsShortModel

@Dao
interface ImageDataDAO {
	@Insert(onConflict = REPLACE)
	fun insertAll(images: List<ImageData>)

	@Query("SELECT count(*) FROM images")
	fun getCount(): Int

	@Query("SELECT imageId, userName, tags, previewUrl FROM images WHERE queryId = :queryId AND pageNum = :pageNum")
	fun loadPage(queryId: Int, pageNum: Int): List<ImageDetailsShortModel>

	@Query("SELECT imageUrl, userName, tags, likes, downloads, comments FROM images WHERE imageId = :imageId")
	fun loadImageDetails(imageId: Int): ImageDetailsModel
}