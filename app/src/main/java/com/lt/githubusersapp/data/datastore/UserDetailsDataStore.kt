package com.lt.githubusersapp.data.datastore

import com.lt.githubusersapp.data.UserDetailsEntity
import com.lt.githubusersapp.data.UsersDataSource
import com.lt.githubusersapp.data.local.UserDatabase
import javax.inject.Inject

class UserDetailsDataStore @Inject constructor(
    private val db: UserDatabase,
    private val usersRemoteDataSource: UsersDataSource
) {
    suspend fun getUserDetails(login: String): UserDetailsEntity {
        var userDetails = db.dao.getUserDetails(login)
        if (userDetails == null) {

            val remoteUserDetails = usersRemoteDataSource.getUser(login)
            userDetails = remoteUserDetails
            db.dao.upsertUserDetails(userDetails)
        }
        return userDetails
    }
}