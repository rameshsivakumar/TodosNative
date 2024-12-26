package com.sample.todosnative.di

import android.content.Context
import com.sample.todosnative.db.TodoDatabase
import com.sample.todosnative.network.ApiService
import com.sample.todosnative.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return ApiService.create()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TodoDatabase {
        return TodoDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideTodoRepository(apiService: ApiService, database: TodoDatabase): TodoRepository {
        return TodoRepository(apiService, database.todoDao())
    }
}