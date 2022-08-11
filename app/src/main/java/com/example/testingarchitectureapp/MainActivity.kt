package com.example.testingarchitectureapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), INotesRVAdapter {

    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter  = NoteRVAdapter(this,this)
        recyclerView.adapter = adapter


        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this) { list ->
            list?.let {
                adapter.updateList(it)
            }
        }
    }

    override fun onItemClicked(note: Note) {
        viewModel.delete(note)
        Toast.makeText(this,"${note.text} Deleted",Toast.LENGTH_SHORT).show()
    }

    fun submitData(view: View) {
        val input = findViewById<EditText>(R.id.input)
        val noteText = input.text.toString()
        if(noteText.isNotEmpty()){
            viewModel.insertNote(Note(noteText))
            Toast.makeText(this,"$noteText Deleted",Toast.LENGTH_SHORT).show()
        }
    }
}