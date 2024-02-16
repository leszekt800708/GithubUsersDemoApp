package com.lt.githubusersapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.lt.githubusersapp.Screens
import com.lt.githubusersapp.domain.User

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel,
    navController: NavHostController,
    onError: (String) -> Unit
) {
    val usersPagingItems: LazyPagingItems<User> = viewModel.usersState.collectAsLazyPagingItems()
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
                            text = errorMessage ?: "Unknown error",
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
                            text = "Error loading data",
                            modifier = Modifier.fillMaxWidth().padding(16.dp),
                            color = Color.Red
                        )
                    }
                }
            }
        }
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
            contentDescription = null
        )
        Text(
            text = user.login,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
    }
    Divider(thickness = 1.dp, color = Color.Black, modifier = Modifier.padding(vertical = 4.dp))
}

@Composable
@Preview
fun ResultRowPreview() {
    ResultRow(
        user = User("1", "testUser", ""),
        onClick = {}
    )
}
