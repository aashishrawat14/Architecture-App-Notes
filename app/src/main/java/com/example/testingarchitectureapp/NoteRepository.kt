package com.example.testingarchitectureapp

import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao) {
    val allNote :LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note: Note){
        noteDao.insert(note)
    }

    suspend fun delete(note: Note){
        noteDao.delete(note)
    }

}