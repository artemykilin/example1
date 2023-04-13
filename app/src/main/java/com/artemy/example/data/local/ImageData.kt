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
	val imageId: Int,
	val tags: String,
	val userName: String,
	val likes: Int,
	val downloads: Int,
	val comments: Int,
	val previewUrl: String,
	val imageUrl: String,
	val queryId: Int,
	val pageNum: Int,
	@PrimaryKey(autoGenerate = true) val id: Int = 0
)