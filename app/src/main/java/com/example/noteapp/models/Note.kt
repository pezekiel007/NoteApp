package com.example.noteapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity("notes_table")
data class Note(

    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo("title") val title: String?,
    @ColumnInfo("note") val note: String?,
    @ColumnInfo("date") val date: String?

): Serializable
