package com.lt.githubusersapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lt.githubusersapp.data.UserDetailsEntity
import com.lt.githubusersapp.data.UserEntity

@Database(
    entities = [UserEntity::class, UserDetailsEntity::class],
    version = 1
)
abstract class UserDatabase : RoomDatabase() {
    abstract val dao: UserDao
}