package com.example.lumoscoop3.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface MusicDao {

    @Upsert
    suspend fun upsertMusic(music: Music)

    @Delete
    suspend fun deleteMusic(music: Music)

    @Query("SELECT * FROM music ORDER BY title ASC")
    fun getMusicsOrderedByTitle(): Flow<List<Music>>

}