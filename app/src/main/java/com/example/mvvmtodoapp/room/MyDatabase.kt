package com.example.mvvmtodoapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvvmtodoapp.data.entity.ToDos

@Database(entities = [ToDos::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun getToDosDao(): ToDosDao
}