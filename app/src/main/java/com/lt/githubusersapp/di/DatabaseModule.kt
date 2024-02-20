package com.lt.githubusersapp.di

import android.content.Context
import androidx.room.Room
import com.lt.githubusersapp.data.local.UserDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @ApplicationScope
    @Provides
    fun provideUserDatabase(context: Context): UserDatabase {
        return Room.databaseBuilder(context, UserDatabase::class.java, "user_database")
            .build()
    }
}