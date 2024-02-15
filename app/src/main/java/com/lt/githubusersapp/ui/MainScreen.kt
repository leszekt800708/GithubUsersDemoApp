package com.lt.githubusersapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.lt.githubusersapp.Screens
import com.lt.githubusersapp.domain.User

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel,
    navController: NavHostController,
    onError: (String) -> Unit
) {
    val state by viewModel.state.collectAsState()
    when (val currentState = state) {
        is MainScreenState.Init -> {
            viewModel.fetchUsers()
        }

        is MainScreenState.MainSuccess -> {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(currentState.users) { user ->
                    ResultRow(
                        user = user,
                        onClick = {
                            navController.navigate("${Screens.DETAILS.screenName}/${user.login}")
                        },
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }

        is MainScreenState.Loading -> {
            if (currentState.isLoading) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }

        is MainScreenState.Error -> {
            onError(currentState.message)
        }
    }
}

@Composable
fun ResultRow(user: User, onClick: (String) -> Unit, modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth().clickable {
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
        Divider(thickness = 1.dp, color = Color.Black)
    }
}

@Composable
@Preview
fun ResultRowPreview() {
    ResultRow(
        user = User("1", "testUser", ""),
        onClick = {}
    )
}
