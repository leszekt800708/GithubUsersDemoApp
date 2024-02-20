package com.lt.githubusersapp.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.lt.githubusersapp.data.UserDetailsEntity
import com.lt.githubusersapp.data.UserEntity

@Dao
interface UserDao {
    @Upsert
    suspend fun upsertAll(users: List<UserEntity>)

    @Query("SELECT * FROM userentity")
    fun pagingSource(): PagingSource<Int, UserEntity>

    @Query("DELETE FROM userentity")
    fun clearAllUsers()


    @Upsert
    suspend fun upsertUserDetails(userDetails: UserDetailsEntity)

    @Query("SELECT * FROM userdetailsentity WHERE  login = :login ")
    fun getUserDetails(login: String): UserDetailsEntity?

    @Query("DELETE FROM userdetailsentity")
    fun clearAllUserDetails()
}