package com.lt.githubusersapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.lt.githubusersapp.domain.UserDetails

@Composable
fun DetailsScreen(
    login: String,
    viewModel: DetailsScreenViewModel,
    navController: NavController,
    onError: (String) -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(login) {
        viewModel.fetchUserDetails(login)
    }
    when (val currentState: DetailsScreenState = state) {
        is DetailsScreenState.Loading -> {
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

        is DetailsScreenState.Error -> {
            onError(currentState.message)
        }

        is DetailsScreenState.DetailsSuccess -> {
            val userDetails: UserDetails = currentState.userDetails
            Column(
                modifier = Modifier.fillMaxWidth()
                    .fillMaxHeight()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    modifier = Modifier.height(120.dp),
                    contentScale = ContentScale.Fit,
                    model = userDetails.avatarUrl,
                    contentDescription = null
                )
                Text(
                    text = userDetails.login,
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = userDetails.bio,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Start
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp).fillMaxWidth()
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "${userDetails.publicRepos}",
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Public Repos",
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "${userDetails.followers}",
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Followers",
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "${userDetails.following}",
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Following",
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

        else -> {}
    }
}