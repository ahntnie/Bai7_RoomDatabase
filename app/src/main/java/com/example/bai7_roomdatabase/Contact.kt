package com.example.bai7_roomdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val Id: Int = 0,
    @ColumnInfo
    val Phone: String ="",
    @ColumnInfo
    val FullName: String ="",
    @ColumnInfo
    val Email:String = ""
)
