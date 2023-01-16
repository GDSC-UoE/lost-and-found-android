package com.gdsc_uoe.lostnfound

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gdsc_uoe.lostnfound.navigation.BottomNavigationBar
import com.gdsc_uoe.lostnfound.navigation.BottomNavigationItem
import com.gdsc_uoe.lostnfound.navigation.LostnFoundNavigation
import com.gdsc_uoe.lostnfound.navigation.LostnFoundScreens
import com.gdsc_uoe.lostnfound.ui.theme.LostnFoundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LostnFoundApp()
        }
    }
}

@Composable
fun LostnFoundApp() {
    LostnFoundTheme {

        val navController = rememberNavController()
        var showBottomBar by rememberSaveable { mutableStateOf(true) }
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        showBottomBar = when (navBackStackEntry?.destination?.route) {
            LostnFoundScreens.SplashScreen.name -> false
            else -> true
        }


        Scaffold(bottomBar = {
            if (showBottomBar) {
                BottomNavigationBar(
                    items =
                    listOf(
                        BottomNavigationItem(
                            name = "Search",
                            route = LostnFoundScreens.SearchScreen.name,
                            icon = R.drawable.ic_search_item
                        ),
                        BottomNavigationItem(
                            name = "Post",
                            route = LostnFoundScreens.PostScreen.name,
                            icon = R.drawable.ic_post_item
                        ),
                        BottomNavigationItem(
                            name = "Chats",
                            route = LostnFoundScreens.ChatsScreen.name,
                            icon = R.drawable.ic_chat
                        ),
                        BottomNavigationItem(
                            name = "Profile",
                            route = LostnFoundScreens.ProfileScreen.name,
                            icon = R.drawable.ic_profile
                        )
                    ),
                    navController = navController,
                    onItemClick = { navController.navigate(it.route) })
            }
        }) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                Column {
                    LostnFoundNavigation(navController = navController)
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LostnFoundTheme {
        LostnFoundApp()
    }
}