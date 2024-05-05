package com.example.mvvmtodoapp.di

import android.content.Context
import androidx.room.Room
import com.example.mvvmtodoapp.data.dataSource.ToDosDataSource
import com.example.mvvmtodoapp.data.repo.ToDosRepository
import com.example.mvvmtodoapp.room.MyDatabase
import com.example.mvvmtodoapp.room.ToDosDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideToDosRepository(toDosDataSource: ToDosDataSource): ToDosRepository {
        return ToDosRepository(toDosDataSource)
    }

    @Provides
    @Singleton
    fun provideToDosDataSource(toDosDao: ToDosDao): ToDosDataSource {
        return ToDosDataSource(toDosDao)
    }

    @Provides
    @Singleton
    fun provideToDosDao(@ApplicationContext context: Context): ToDosDao {
        val db = Room.databaseBuilder(context, MyDatabase::class.java, "todos_app.db")
           // .createFromAsset("todos_app.sqlite")
            .build()
        return db.getToDosDao()
    }
}