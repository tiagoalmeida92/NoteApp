package com.task.noteapp.list

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.coroutineScope
import com.task.noteapp.note.Note
import com.task.noteapp.databinding.ActivityMainBinding
import com.tiago.feature.navigation.Navigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NoteAdapter.OnNoteClicked {

    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpClickListeners()
        setUpRecyclerView()
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycle.coroutineScope.launch {
            viewModel.items.collect {
                render(it)
            }
        }
    }

    private fun render(notes: List<Note>) {
        adapter.items = notes
        if (notes.isEmpty()) {
            binding.listEmptyState.visibility = VISIBLE
        } else {
            binding.listEmptyState.visibility = GONE
        }
    }

    private fun setUpRecyclerView() {
        adapter = NoteAdapter(this)
        binding.notesRecyclerView.adapter = adapter
    }

    private fun setUpClickListeners() {
        binding.listCreate.setOnClickListener {
            Navigator.goToAddEditNote(this, binding.listCreate, null)
        }
    }

    override fun onNoteClicked(note: Note, view: View) {
        Navigator.goToAddEditNote(this, view, note)
    }
}