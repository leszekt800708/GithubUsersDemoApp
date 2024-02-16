package com.lt.githubusersapp.remote.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import coil.network.HttpException
import com.lt.githubusersapp.data.UsersDataSource
import com.lt.githubusersapp.domain.User
import java.io.IOException
import javax.inject.Inject

class GithubUsersPagingSource @Inject constructor(
    private val usersDataSource: UsersDataSource
) : PagingSource<Int, User>() {
    private val pageAnchors = mutableListOf<Int>()
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        try {
            val startId = params.key ?: 0
            val perPage = params.loadSize
            val users = usersDataSource.getUsers(perPage, startId).map {
                User(
                    id = it.id,
                    login = it.login,
                    avatarUrl = it.avatarUrl
                )
            }
            val firstIdOnPage = users.first().id.toInt()
            pageAnchors.add(firstIdOnPage)
            return LoadResult.Page(
                data = users,
                prevKey = if (startId <= 1) {
                    null
                } else {
                    val index = pageAnchors.indexOf(firstIdOnPage)
                    pageAnchors[index - 1]
                },
                nextKey = if (users.isEmpty()) null else users.last().id.toInt()
            )
        } catch (ex: IOException) {
            return LoadResult.Error(ex)
        } catch (ex: HttpException) {
            return LoadResult.Error(ex)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition
    }
}