package com.artemy.example.data.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
	tableName = "images",
	foreignKeys = [ForeignKey(entity = RemoteQuery::class, parentColumns = ["id"], childColumns = ["queryId"], onDelete = CASCADE)],
	indices = [Index("queryId")]
)
data class ImageData(
	var imageId: Int,
	var tags: String,
	var userName: String,
	var likes: Int,
	var downloads: Int,
	var comments: Int,
	var previewUrl: String,
	var imageUrl: String,
	var queryId: Int,
	var pageNum: Int,
	@PrimaryKey(autoGenerate = true) val id: Int = 0
)