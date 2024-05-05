package com.example.mvvmtodoapp.data.dataSource

import android.util.Log
import com.example.mvvmtodoapp.data.entity.ToDos
import com.example.mvvmtodoapp.room.ToDosDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ToDosDataSource(private var toDosDao: ToDosDao) {

    private var TAG = "ToDosDataSource"

    suspend fun save(name: String) {
        val newToDo = ToDos(0, name)
        toDosDao.save(newToDo)
        Log.d(TAG, name)
    }

    suspend fun update(id: Int, name: String) {
        val updatedToDo = ToDos(id, name)
        toDosDao.update(updatedToDo)
        Log.d(TAG, "id: $id - name: $name")
    }

    suspend fun delete(id: Int) {
        val deleteToDo = ToDos(id, "")
        toDosDao.delete(deleteToDo)
        Log.d(TAG, "id: $id")
    }

    suspend fun loadAllToDos(): List<ToDos> = withContext(Dispatchers.IO) {
        return@withContext toDosDao.loadAllToDos()
    }

    suspend fun searchToDos(searchText: String): List<ToDos> = withContext(Dispatchers.IO) {
        Log.d(TAG, "Searching: $searchText")
        return@withContext toDosDao.search(searchText)
    }
}