package com.example.mvvmtodoapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mvvmtodoapp.data.entity.ToDos

@Dao
interface ToDosDao {

    @Query("Select * From toDos")
    suspend fun loadAllToDos(): List<ToDos>

    @Insert
    suspend fun save(toDos: ToDos)

    @Update
    suspend fun update(toDos: ToDos)

    @Delete
    suspend fun delete(toDos: ToDos)

    @Query("Select * From toDos WHERE name like '%' || :searchText || '%' ")
    suspend fun search(searchText: String): List<ToDos>
}