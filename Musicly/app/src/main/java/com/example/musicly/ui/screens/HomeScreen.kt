package com.example.musicly.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.musicly.core.router.AppRoutes
import com.example.musicly.ui.composable.DrawerItem
import com.example.musicly.ui.composable.drawerItems
import com.example.musicly.ui.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    val viewModel: MainViewModel = viewModel()
    val controller = rememberNavController()
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope: CoroutineScope = rememberCoroutineScope()


    val currentScreen = remember {
        viewModel.currentScreen.value
    }
    val title = remember {
        mutableStateOf<String>(currentScreen.title)
    }
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {


                LazyColumn(content = {


                    items(drawerItems) { item ->
                        DrawerItem(selected = false, item = item) {

                            when (item.route) {
                                AppRoutes.addAccountScreen -> println()
                                AppRoutes.subscribtionScreen -> println()
                                else -> println()
                            }
                        }


                        Divider()
                    }
                })

            }
        },
        drawerState = drawerState,
    ) {
        Scaffold(

            topBar = {
                TopAppBar(
                    title = {
                        Text(text = title.value)
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    }
                )
            },

            ) {

            Column(
                modifier =
                Modifier.padding(it)
            ) {

            }
        }
    }


}