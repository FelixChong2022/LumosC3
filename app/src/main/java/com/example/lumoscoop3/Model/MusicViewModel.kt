package com.example.lumoscoop3.Model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lumoscoop3.Room.Music
import com.example.lumoscoop3.Room.MusicDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MusicViewModel(
    private val dao: MusicDao
): ViewModel() {

    private val _state = MutableStateFlow(MusicState())
    private val _musics = dao.getMusicsOrderedByTitle()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val state = combine(_state, _musics) { state, musics ->
        state.copy(
            musics= musics
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), MusicState())

    fun onEvent(event: MusicEvent) {
        when(event) {
            is MusicEvent.DeleteMusic -> {
                viewModelScope.launch {
                    dao.deleteMusic(event.music)
                }
            }
            MusicEvent.HideDialog -> {
                _state.update { it.copy(
                    isAddingMusic = false
                ) }
            }
            MusicEvent.SaveMusic -> {
                val title = state.value.title

                if(title.isBlank()) {
                    return
                }

                val music = Music(
                    title = title
                )
                viewModelScope.launch {
                    dao.upsertMusic(music)
                }
                _state.update { it.copy(
                    isAddingMusic = false,
                    title = ""
                ) }
            }
            is MusicEvent.SetTitle -> {
                _state.update { it.copy(
                    title = event.title
                ) }
            }
            MusicEvent.ShowDialog -> {
                _state.update { it.copy(
                    isAddingMusic = true
                ) }
            }
        }
    }
}
