package me.arunajayan.notesapp.data.note

import me.arunajayan.notesapp.database.NoteDatabase
import me.arunajayan.notesapp.domain.DateTimeUtil
import me.arunajayan.notesapp.domain.note.Note
import me.arunajayan.notesapp.domain.note.NoteDataSource

class SqlDelightNoteDataSource(db: NoteDatabase): NoteDataSource {

    private val queries = db.notesQueries

    override suspend fun insertNote(note: Note) {
        queries.insertNote(
            id = note.id,
            title = note.title,
            content = note.content,
            colorHex = note.colorHex,
            created = DateTimeUtil.toEpochMillis(note.created)
        )
    }

    override suspend fun getNoteById(id: Long): Note? {
        return queries
            .getNoteById(id)
            .executeAsOneOrNull()
            ?.toNote()
    }

    override suspend fun getAllNotes(): List<Note> {
        return queries
            .getAllNotes()
            .executeAsList()
            .map { it.toNote() }
    }

    override suspend fun deleteNoteById(id: Long) {
        queries.deleteNoteById(id)
    }
}