package com.example.lumoscoop3.Model

import com.example.lumoscoop3.Room.Music

sealed interface MusicEvent {

    object SaveMusic: MusicEvent
    data class SetTitle(val title: String): MusicEvent
    object ShowDialog: MusicEvent
    object HideDialog: MusicEvent
    data class DeleteMusic (val music: Music): MusicEvent

}