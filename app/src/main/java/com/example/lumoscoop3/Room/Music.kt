package com.example.lumoscoop3.Room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Music(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="music_id")
    val musicId: Int = 0,
    val title: String
)
