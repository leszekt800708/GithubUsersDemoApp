package com.lt.githubusersapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lt.githubusersapp.di.AppComponent
import com.lt.githubusersapp.ui.DetailsScreen
import com.lt.githubusersapp.ui.MainScreen
import com.lt.githubusersapp.ui.ViewModelFactory
import com.lt.githubusersapp.ui.theme.GithubUsersAppTheme
import kotlinx.coroutines.launch

val LocalAppComponent = staticCompositionLocalOf<AppComponent> {
    error("No Dagger Application Component found!")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent = (applicationContext as GithubUsersApplication).appComponent
        val factory = ViewModelFactory(appComponent)
        setContent {
            CompositionLocalProvider(LocalAppComponent provides appComponent)
            {
                GithubUsersAppTheme {
                    GithubUsersApp(factory)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GithubUsersApp(factory: ViewModelFactory) {
    val navController = rememberNavController()
    val snackBarHostState = remember { SnackbarHostState() }
    val snackBarCoroutineScope = rememberCoroutineScope()

    val onError: (String) -> Unit = { message ->
        snackBarCoroutineScope.launch {
            snackBarHostState.showSnackbar(message)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.app_title), textAlign = TextAlign.Center) },
                navigationIcon = {
                    val backStackEntry by navController.currentBackStackEntryAsState()
                    if (backStackEntry != null && navController.previousBackStackEntry != null) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                },
            )
        },

        snackbarHost = { SnackbarHost(snackBarHostState) }) {
        GithubUsersNavHost(
            navController = navController,
            factory = factory,
            modifier = Modifier.padding(it),
            onError = onError,
        )
    }
}

@Composable
fun GithubUsersNavHost(
    navController: NavHostController,
    factory: ViewModelFactory,
    onError: (String) -> Unit,
    modifier: Modifier = Modifier
) {


    NavHost(navController = navController, startDestination = Screens.MAIN.screenName, modifier = modifier) {
        composable(Screens.MAIN.screenName) {
            MainScreen(
                viewModel = viewModel(factory = factory),
                navController = navController
            )
        }
        composable("${Screens.DETAILS.screenName}/{login}") {
            val login = (it.arguments?.getString("login")) ?: ""
            DetailsScreen(
                login = login,
                viewModel = viewModel(factory = factory),
                onError = onError
            )
        }
    }
}

enum class Screens(val screenName: String) {
    MAIN("main_screen"),
    DETAILS("details_screen")
}