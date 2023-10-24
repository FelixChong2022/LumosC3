package com.example.lumoscoop3.Model

import com.example.lumoscoop3.Room.Music

data class MusicState(
    val musics: List<Music> = emptyList(),
    val title: String = "",
    val isAddingMusic: Boolean = false
)