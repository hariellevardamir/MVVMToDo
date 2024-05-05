package com.example.mvvmtodoapp.data.repo

import com.example.mvvmtodoapp.data.dataSource.ToDosDataSource
import com.example.mvvmtodoapp.data.entity.ToDos

class ToDosRepository(
    var toDosDataSource: ToDosDataSource
) {

    suspend fun save(name: String) {
        toDosDataSource.save(name)
    }

    suspend fun update(id: Int, name: String) {
        toDosDataSource.update(id, name)
    }

    suspend fun delete(id: Int) {
        toDosDataSource.delete(id)
    }

    suspend fun loadAllToDos(): List<ToDos> {
        return toDosDataSource.loadAllToDos()
    }

    suspend fun searchToDos(searchText: String): List<ToDos> {
        return toDosDataSource.searchToDos(searchText)
    }
}