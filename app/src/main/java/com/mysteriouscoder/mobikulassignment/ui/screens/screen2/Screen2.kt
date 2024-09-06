package com.mysteriouscoder.mobikulassignment.ui.screens.screen2

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.mysteriouscoder.mobikulassignment.UserItem
import com.mysteriouscoder.mobikulassignment.UserPostsScreen
import com.mysteriouscoder.mobikulassignment.ui.theme.BabyBlue
import com.mysteriouscoder.mobikulassignment.ui.theme.LightGreen
import com.mysteriouscoder.mobikulassignment.ui.theme.RedOrange
import com.mysteriouscoder.mobikulassignment.ui.theme.RedPink
import com.mysteriouscoder.mobikulassignment.ui.theme.Violet

//Top Bar Tab
//@Composable
//fun Screen2(userId: Int,
//            navController: NavHostController
//
//) {
//    val viewModel = viewModel<Screen2ViewModel>(factory = Screen2ViewModelFactory(userId))
//    val userInfo by viewModel.userInfo.collectAsState()
//    val userPosts by viewModel.userPosts.collectAsState()
//    var selectedTabIndex by remember { mutableStateOf(0) }
//
//    Column {
//        // Tab Row for switching between User Info and User Posts
//        TabRow(selectedTabIndex = selectedTabIndex) {
//            Tab(selected = selectedTabIndex == 0, onClick = { selectedTabIndex = 0 }) {
//                Text("User Info", modifier = Modifier.padding(16.dp))
//            }
//            Tab(selected = selectedTabIndex == 1, onClick = { selectedTabIndex = 1 }) {
//                Text("User Posts", modifier = Modifier.padding(16.dp))
//            }
//        }
//        val colors = when (userId % 5) {
//            1 -> LightGreen
//            2 -> RedOrange
//            3 -> RedPink
//            4 -> BabyBlue
//            else -> Violet
//        }
//
//        when (selectedTabIndex) {
//            0 -> {
//                // User Info Tab
//                userInfo?.let { user ->
//                    Spacer(modifier = Modifier.height(16.dp))
//                    UserItem(user,
//                        color = colors
//                    )
////                    UserInfoScreen(user)
//                } ?: CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
//            }
//            1 -> {
//                // User Posts Tab
//                UserPostsScreen(userPosts,
//                    navController = navController)
//            }
//        }
//    }
//}


//Bottom Tab
@Composable
fun Screen2(userId: Int,
            navController: NavHostController

) {
        val viewModel = viewModel<Screen2ViewModel>(factory = Screen2ViewModelFactory(userId))
    val userInfo by viewModel.userInfo.collectAsState()
    val userPosts by viewModel.userPosts.collectAsState()
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }
//
    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = "User Info",
                    modifier = Modifier
                        .clickable { selectedTabIndex = 0 }
                        .padding(10.dp),
                    color = if (selectedTabIndex == 0) Color.Blue else Color.Gray
                )
                Text(
                    text = "User Posts",
                    modifier = Modifier
                        .clickable { selectedTabIndex = 1 }
                        .padding(10.dp),
                    color = if (selectedTabIndex == 1) Color.Blue else Color.Gray
                )
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(it) // Adjust padding based on Scaffold
        ) {
            val colors = when (userId % 5) {
                1 -> LightGreen
                2 -> RedOrange
                3 -> RedPink
                4 -> BabyBlue
                else -> Violet
            }

            when (selectedTabIndex) {
                0 -> {
                    // User Info Tab
                    userInfo?.let { user ->
                        Spacer(modifier = Modifier.height(16.dp))
                        UserItem(user, color = colors)
                    } ?: CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }
                1 -> {
                    // User Posts Tab
                    UserPostsScreen(userPosts, navController = navController)
                }
            }
        }
    }
}

