package com.lt.githubusersapp.data.datastore

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import coil.network.HttpException
import com.lt.githubusersapp.data.UserEntity
import com.lt.githubusersapp.data.UsersDataSource
import com.lt.githubusersapp.data.local.UserDatabase
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class UsersPagedDataStore @Inject constructor(
    private val db: UserDatabase,
    private val usersRemoteDataSource: UsersDataSource
) : RemoteMediator<Int, UserEntity>() {
    override suspend fun load(loadType: LoadType, state: PagingState<Int, UserEntity>): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)

                LoadType.APPEND -> {
                    val lastUser = state.lastItemOrNull()
                    lastUser?.id?.toInt() ?: 0
                }
            }

            val users = usersRemoteDataSource.getUsers(
                perPage = state.config.pageSize,
                startId = loadKey
            )
            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    db.dao.clearAllUsers()
                    db.dao.clearAllUserDetails()
                }
                db.dao.upsertAll(users)
            }
            MediatorResult.Success(endOfPaginationReached = users.isEmpty())
        } catch (ex: IOException) {
            MediatorResult.Error(ex)
        } catch (ex: HttpException) {
            MediatorResult.Error(ex)
        }
    }
}