package me.arunajayan.notesapp.android.note_list

import me.arunajayan.notesapp.domain.note.Note


data class NoteListState(
    val notes: List<Note> = emptyList(),
    val searchText: String = "",
    val isSearchActive: Boolean = false
)
