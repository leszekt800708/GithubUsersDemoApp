package com.lt.githubusersapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.lt.githubusersapp.R
import com.lt.githubusersapp.Screens
import com.lt.githubusersapp.domain.User
import eu.bambooapps.material3.pullrefresh.PullRefreshIndicator
import eu.bambooapps.material3.pullrefresh.pullRefresh
import eu.bambooapps.material3.pullrefresh.rememberPullRefreshState

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun MainScreen(
    viewModel: MainScreenViewModel,
    navController: NavHostController
) {
    val usersPagingItems: LazyPagingItems<User> = viewModel.usersState.collectAsLazyPagingItems()
    var refreshing by remember { mutableStateOf(false) }
    val state = rememberPullRefreshState(refreshing, { usersPagingItems.refresh() })
    Box(Modifier.pullRefresh(state)) {
        LazyColumn {
            items(usersPagingItems.itemCount) {
                val user = usersPagingItems[it]
                if (user != null) {
                    ResultRow(
                        user = user,
                        onClick = {
                            navController.navigate("${Screens.DETAILS.screenName}/${user.login}")
                        },
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
            usersPagingItems.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item {
                            CircularProgressIndicator(
                                modifier = Modifier.fillMaxWidth().padding(16.dp)
                            )
                        }
                    }

                    loadState.refresh is LoadState.Error -> {
                        val errorMessage = (loadState.refresh as LoadState.Error).error.message
                        item {
                            Text(
                                text = errorMessage ?: stringResource(R.string.unknown_error),
                                modifier = Modifier.fillMaxWidth().padding(16.dp),
                                color = Color.Red
                            )
                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        item {
                            CircularProgressIndicator(
                                modifier = Modifier.fillMaxWidth().padding(16.dp)
                            )
                        }
                    }

                    loadState.append is LoadState.Error -> {
                        item {
                            Text(
                                text = stringResource(R.string.error_loading_data),
                                modifier = Modifier.fillMaxWidth().padding(16.dp),
                                color = Color.Red
                            )
                        }
                    }
                }
            }
        }
        PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))
    }
}


@Composable
fun ResultRow(user: User, onClick: (String) -> Unit, modifier: Modifier = Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier.fillMaxWidth().clickable {
        onClick(user.login)
    }) {
        AsyncImage(
            modifier = Modifier.height(60.dp),
            model = user.avatarUrl,
            contentDescription = null,
            placeholder = painterResource(R.drawable.baseline_person_24)

        )
        Text(
            text = user.login,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
    }
    HorizontalDivider(thickness = 1.dp, color = Color.Black, modifier = Modifier.padding(vertical = 4.dp))
}

@Composable
@Preview
fun ResultRowPreview() {
    ResultRow(
        user = User("1", "testUser", ""),
        onClick = {}
    )
}
