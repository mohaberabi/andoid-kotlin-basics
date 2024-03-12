package com.example.wishify.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val wishTable = "wishTable"

@Entity(tableName = wishTable)
public data class WishItem(
    @ColumnInfo("name")
    val name: String,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo("desc")
    val describtion: String
)

