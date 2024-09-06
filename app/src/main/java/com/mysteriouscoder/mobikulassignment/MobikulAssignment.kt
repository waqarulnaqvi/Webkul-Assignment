package com.mysteriouscoder.mobikulassignment

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mysteriouscoder.mobikulassignment.navigation.Navigationitems
import com.mysteriouscoder.mobikulassignment.navigation.customdialog.CustomDialogViewModel
import com.mysteriouscoder.mobikulassignment.navigation.customdialog.dialog.ConnectUsDialog
import com.mysteriouscoder.mobikulassignment.navigation.customdialog.dialog.ExitDialog
import com.mysteriouscoder.mobikulassignment.ui.screens.screen1.Screen1
import com.mysteriouscoder.mobikulassignment.ui.screens.screen2.Screen2
import com.mysteriouscoder.mobikulassignment.ui.screens.screen3.Screen3


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MobikulAssignment(navigationController: NavHostController) {
    val context = LocalContext.current
    val sendEmailIntent = remember {
        mutableStateOf(Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:syedwaqarul786@gmail.com")
            putExtra(Intent.EXTRA_SUBJECT, "Feedback")
        })
    }

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { _ -> }
    val showConnectUsDialog = remember { mutableStateOf(false) }
    val showExitDialog = remember { mutableStateOf(false) }


    if (showConnectUsDialog.value) {
        ConnectUsDialog(
            onDismiss = {
                showConnectUsDialog.value = false
                CustomDialogViewModel.onDismissDialog()
            },
        )
    }

    if (showExitDialog.value) {
        ExitDialog(
            onDismiss = {
                showExitDialog.value = false
                CustomDialogViewModel.onDismissDialog()
            },
        )
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "\tMobikul Assignment",
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.nunito_extrabold)),
                        overflow = TextOverflow.Ellipsis,//It makes sure when the title would be not fit then it put ... at the end
                        maxLines = 1 // Limit the text to a single line
                        )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    var showMenu by remember { mutableStateOf(false) }
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(
                            Icons.Default.MoreVert,
                            contentDescription = "More options",
                            tint = Color.White
                        )
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Connect") },
                            onClick = {
                                showMenu = false
                                CustomDialogViewModel.onClick()
                                if (CustomDialogViewModel.isDialogShown) {
                                    showConnectUsDialog.value = true
                                }
                            }
                        )

                        DropdownMenuItem(
                            text = { Text("Mail") },
                            onClick = {
                                showMenu = false
                                launcher.launch(sendEmailIntent.value)
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Rate") },
                            onClick = {
                                showMenu = false
                                val appPackageName = context.packageName
                                try {
                                    context.startActivity(
                                        Intent(
                                            Intent.ACTION_VIEW,
                                            Uri.parse("market://details?id=$appPackageName")
                                        )
                                    )
                                } catch (e: android.content.ActivityNotFoundException) {
                                    context.startActivity(
                                        Intent(
                                            Intent.ACTION_VIEW,
                                            Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                                        )
                                    )
                                }
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Share") },
                            onClick = {
                                showMenu = false
                                val message = "Let me recommend this application:\n\n"
                                val appPackageName = context.packageName
                                val sendIntent: Intent = Intent().apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(
                                        Intent.EXTRA_TEXT,
                                        message + "https://play.google.com/store/apps/details?id=$appPackageName"
                                    )
                                    type = "text/plain"
                                }

                                val shareIntent = Intent.createChooser(sendIntent, null)
                                context.startActivity(shareIntent)
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Exit") },
                            onClick = {
                                showMenu = false
                                CustomDialogViewModel.onClick()
                                if (CustomDialogViewModel.isDialogShown) {
                                    showExitDialog.value = true
                                }
                            }
                        )
                    }
                }
            )
        }
    )

    { paddingValues ->
        NavHost(
            navController = navigationController,
            startDestination = Navigationitems.Screen1.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Navigationitems.Screen1.route) {
                Screen1(navigationController)
            }
            composable(Navigationitems.Screen2.route) {
                val id = it.arguments?.getString("id")?.replace("{id}","")
                Screen2(
                    userId = id!!.toInt(),
                    navController = navigationController
                )
            }
            composable(Navigationitems.Screen3.route) {
                val id = it.arguments?.getString("id")?.replace("{id}","")
                Screen3(id!!.toInt())
            }
        }
    }

    BackHandler(onBack = {
        CustomDialogViewModel.onClick()
        if (CustomDialogViewModel.isDialogShown) {
            showExitDialog.value = true
        }
    })
}