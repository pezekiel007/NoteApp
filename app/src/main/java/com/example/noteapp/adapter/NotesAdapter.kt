package com.example.noteapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.R
import com.example.noteapp.models.Note
import com.google.android.material.card.MaterialCardView
import kotlin.random.Random

class NotesAdapter(private val listener: NotesItemClickedListener):
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private var notesList = ArrayList<Note>()
    private var fullList = ArrayList<Note>()

    inner class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardLayout: MaterialCardView = itemView.findViewById(R.id.card_layout)
        val title: TextView = itemView.findViewById(R.id.tvTitle)
        val note: TextView = itemView.findViewById(R.id.tvNote)
        val date: TextView = itemView.findViewById(R.id.tvDate)
    }

    interface NotesItemClickedListener {
        fun onItemClicked(note: Note)
        fun onLongItemClicked(note: Note, cardView: MaterialCardView)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Note>) {

        fullList.clear()
        fullList.addAll(newList)

        notesList.clear()
        notesList.addAll(newList)

        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filterList(search: String) {

        notesList.clear()

        for (item in fullList) {

            if (item.title?.lowercase()?.contains(search.lowercase()) == true ||
                item.note?.lowercase()?.contains(search.lowercase()) == true
            ) {
                notesList.add(item)
            }
        }

        notifyDataSetChanged()
    }

    private fun randomColor(): Int {
        val list = ArrayList<Int>()

        list.add(R.color.note_color_1)
        list.add(R.color.note_color_2)
        list.add(R.color.note_color_3)
        list.add(R.color.note_color_4)
        list.add(R.color.note_color_5)
        list.add(R.color.note_color_6)

        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random(seed).nextInt(list.size)
        return list[randomIndex]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return NotesViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currentNote = notesList[position]
        holder.title.text = currentNote.title
        holder.title.isSelected = true

        holder.note.text = currentNote.note
        holder.date.text = currentNote.date
        holder.date.isSelected = true

        holder.cardLayout.setCardBackgroundColor(
            holder.itemView.resources.getColor(
                randomColor(),
                null
            )
        )

        holder.cardLayout.setOnClickListener {
            listener.onItemClicked(notesList[holder.adapterPosition])
        }

        holder.cardLayout.setOnLongClickListener {
            listener.onLongItemClicked(notesList[holder.adapterPosition], holder.cardLayout)
            true
        }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

}
