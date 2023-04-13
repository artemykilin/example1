package com.artemy.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "remote_queries")
data class RemoteQuery(
	val text: String,
	val timestamp: Date,
	var totalPages: Int,
	var loadedPages: Int,
	@PrimaryKey(autoGenerate = true) val id: Int = 0
) {
	fun needsLoadPage(page: Int): Boolean {
		return if (totalPages == -1) true else page in (loadedPages + 1)..totalPages
	}

	fun isLast(page: Int): Boolean = page == totalPages
}
