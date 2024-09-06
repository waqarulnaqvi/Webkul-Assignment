package com.mysteriouscoder.mobikulassignment.ui.screens.screen1

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.mysteriouscoder.mobikulassignment.Heading
import com.mysteriouscoder.mobikulassignment.UserItem
import com.mysteriouscoder.mobikulassignment.navigation.Navigationitems
import com.mysteriouscoder.mobikulassignment.ui.theme.BabyBlue
import com.mysteriouscoder.mobikulassignment.ui.theme.LightGreen
import com.mysteriouscoder.mobikulassignment.ui.theme.RedOrange
import com.mysteriouscoder.mobikulassignment.ui.theme.RedPink
import com.mysteriouscoder.mobikulassignment.ui.theme.Violet


@Composable
fun Screen1(
    navController: NavHostController
)
{
    val viewModel = viewModel<Screen1ViewModel>()
    val searchText by viewModel.searchText.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()
    val userList by viewModel.userList.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.scrim)
    ) {
        OutlinedTextField(
            value = searchText,
            onValueChange = viewModel::onSearchTextChange,
            label = {
                Text(
                    text = "Search by Name or ID",
                    color = if (isSystemInDarkTheme()) Color.White else Color.Gray
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 10.dp),
//            placeholder = { Text(text = "Search by Name or ID") },
            singleLine = true,
            shape = MaterialTheme.shapes.extraLarge,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            Spacer(modifier = Modifier.width(20.dp))
            Heading(
                title="User Profile:",
                modifier = Modifier.padding(end=6.dp)
            )
        }
        Spacer(modifier = Modifier.height(5.dp))

        if (isSearching) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.scrim)
            ) {
                item {
                    if (searchText.isNotEmpty()) {
                        Row {
                            Spacer(modifier = Modifier.width(20.dp))
                            Heading(
                                title = "Search Results (${userList.size}):",
                                fontSize = 20,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }

                itemsIndexed(userList) { _, user ->
                    val colors = when (user.id % 5) {
                        1 -> LightGreen
                        2 -> RedOrange
                        3 -> RedPink
                        4 -> BabyBlue
                        else -> Violet
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    UserItem(
                        user,
                        color = colors,
                        onclick = {
                            navController.navigate(Navigationitems.Screen2.route + user.id.toString())
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }

                item {
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}


