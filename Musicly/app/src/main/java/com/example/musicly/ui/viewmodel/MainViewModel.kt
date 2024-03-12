package com.example.musicly.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.musicly.core.router.Screen

class MainViewModel : ViewModel() {


    private val _state: MutableState<Screen> = mutableStateOf(Screen.DrawerScreen.AddAccount)


    val currentScreen: MutableState<Screen>
        get() = _state


    fun changeScreen(scren: Screen) {
        _state.value = scren
    }
}