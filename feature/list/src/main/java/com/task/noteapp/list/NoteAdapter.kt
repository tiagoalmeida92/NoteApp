package com.task.noteapp.list

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.task.noteapp.note.Note
import com.task.noteapp.databinding.ItemNoteBinding
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.DateTimeFormatterBuilder
import org.threeten.bp.format.SignStyle
import org.threeten.bp.temporal.ChronoField

class NoteAdapter(
    private val clickListener: OnNoteClicked
) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    init {
        setHasStableIds(true)
    }

    var items: List<Note> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemNoteBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val note = items[position]
        viewHolder.render(note)
    }

    override fun getItemCount() = items.size

    override fun getItemId(position: Int): Long {
        return items[position].id.toLong()
    }

    class ViewHolder(
        private val binding: ItemNoteBinding,
        private val listener: OnNoteClicked
    ) : RecyclerView.ViewHolder(binding.root) {
        fun render(note: Note) {
            if (note.imageUrl.isNullOrEmpty()) {
                binding.itemNoteImage.visibility = GONE
            } else {
                Picasso.get().load(note.imageUrl).into(binding.itemNoteImage)
                binding.itemNoteImage.visibility = VISIBLE
            }
            binding.itemNoteTitle.text = note.title
            binding.itemNoteDescription.text = note.description
            binding.itemNoteCreated.text = note.created.format(dateFormatter)
            if (note.isEdited) {
                binding.itemNoteEdited.visibility = VISIBLE
            } else {
                binding.itemNoteEdited.visibility = GONE
            }
            binding.root.setOnClickListener {
                listener.onNoteClicked(note, it)
            }
        }
    }

    interface OnNoteClicked {
        fun onNoteClicked(note: Note, view: View)
    }

    companion object {
        val dateFormatter: DateTimeFormatter = DateTimeFormatterBuilder()
            .appendValue(ChronoField.DAY_OF_MONTH, 2)
            .appendLiteral('/')
            .appendValue(ChronoField.MONTH_OF_YEAR, 2)
            .appendLiteral('/')
            .appendValue(ChronoField.YEAR, 4, 10, SignStyle.EXCEEDS_PAD)
            .toFormatter()
    }
}