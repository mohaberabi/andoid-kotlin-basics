package com.example.sevenminworkout

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "exercises")
class ExerciseModel(

    @PrimaryKey(autoGenerate = true)
    private var id: Int,
    @ColumnInfo(name = "name")
    private var name: String,
    @ColumnInfo(name = "image")
    private var image: Int,
    @ColumnInfo(name = "completed")
    private var completed: Boolean,
    @ColumnInfo(name = "selected")
    private var selected: Boolean,

    ) {

    fun getImage(): Int {
        return image
    }

    fun setImage(image: Int) {
        this.image = image
    }

    fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getName(): String {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getCompleted(): Boolean {
        return completed
    }

    fun setCompleted(compelted: Boolean) {
        this.completed = compelted
    }

    fun getSelected(): Boolean {
        return selected
    }

    fun setSeelcted(selected: Boolean) {
        this.selected = selected
    }
}