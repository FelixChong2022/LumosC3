package com.example.lumoscoop3.Room

import androidx.room.Database
import androidx.room.RoomDatabase

// version = 1,2,3.. for database migration

@Database(
    entities = [Music::class],
    version = 1
)
abstract class MusicDatabase: RoomDatabase() {

    abstract val dao: MusicDao

}