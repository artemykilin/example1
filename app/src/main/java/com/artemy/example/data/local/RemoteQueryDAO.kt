package com.artemy.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import androidx.room.Update

@Dao
interface RemoteQueryDAO {
	@Insert(onConflict = IGNORE)
	fun insert(remoteQuery: RemoteQuery)

	@Update
	fun update(remoteQuery: RemoteQuery)

	@Query("SELECT * FROM remote_queries where text = :queryText")
	fun getQuery(queryText: String): RemoteQuery

	@Query("SELECT * FROM remote_queries")
	fun getAll(): List<RemoteQuery>

	@Query("SELECT NOT EXISTS(SELECT * FROM remote_queries WHERE text = :queryText)")
	fun noRequest(queryText: String): Boolean

	@Query("DELETE FROM remote_queries WHERE (strftime('%s', 'now', '-12 hours') - timestamp/1000 > 0)")
	fun deleteOutdatedQueries()
}