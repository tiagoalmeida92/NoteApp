package com.task.noteapp.feature.add.edit

import android.os.Bundle
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.coroutineScope
import com.task.noteapp.note.Note
import com.task.noteapp.feature.add.edit.databinding.ActivityAddEditBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddEditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEditBinding
    private val viewModel: AddEditViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpClickListeners()
        render(viewModel.note)
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycle.coroutineScope.launch {
            launch {
                viewModel.updated.collect { saved ->
                    if (saved) {
                        finish()
                    }
                }
            }
            launch {
                viewModel.error.collect {
                    if (it == ErrorViewState.TitleAndDescriptionError) {
                        showErrorDialog()
                        viewModel.clearError()
                    }
                }
            }
        }
    }

    private fun showErrorDialog() {
        AlertDialog.Builder(this)
            .setPositiveButton(android.R.string.ok, null)
            .setMessage(R.string.feature_add_edit_error_save)
            .show()
    }

    private fun render(note: Note?) {
        if (note != null) {
            binding.addEditTitle.setText(note.title)
            binding.addEditDescription.setText(note.description)
            binding.addEditImageUrl.setText(note.imageUrl)
            binding.addEditDelete.visibility = VISIBLE
        }
    }

    private fun setUpClickListeners() {
        binding.addEditSave.setOnClickListener {
            viewModel.save(
                binding.addEditImageUrl.text.toString(),
                binding.addEditTitle.text.toString(),
                binding.addEditDescription.text.toString()
            )
        }
        binding.addEditDelete.setOnClickListener {
            viewModel.delete()
        }
    }
}