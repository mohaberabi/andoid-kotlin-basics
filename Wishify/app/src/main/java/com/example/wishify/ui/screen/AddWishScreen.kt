package com.example.wishify.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.wishify.R
import com.example.wishify.core.Router.Screen
import com.example.wishify.ui.composable.AppBar
import com.example.wishify.ui.composable.CustomTextField
import com.example.wishify.ui.viewmodels.WishViewModel

@Composable
fun AddWishScreen(
    navHostController: NavHostController,
    wishViewModel: WishViewModel,
    id: Long,
) {
    val state: WishViewModel.WishState = wishViewModel.wishState.value
    val isEdit: Boolean = id == 0L
    val title =
        if (isEdit) stringResource(id = R.string.addWish) else stringResource(id = R.string.editWish)

    Scaffold(topBar = {
        AppBar(title = title,
            showNavIcon = true,
            onBackPressed = {
                navHostController.navigateUp()
            }
        )
    }


    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(10.dp))


            CustomTextField(
                value = state.title, onValueChanged = { v: String ->
                    wishViewModel.titleChanged(v = v)
                }, label = "Title"

            )
            CustomTextField(
                value = state.describtion, onValueChanged = { v: String ->
                    wishViewModel.describtionChanged(v = v)
                }, label = "Describiton"


            )

            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {

                },
                enabled = state.canAdd,
            ) {

                val buttonString = if (isEdit) "Edit" else "Add"
                Text(text = buttonString)
            }
        }
    }


}