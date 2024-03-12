package com.example.musicly.core.router

import androidx.annotation.DrawableRes
import com.example.musicly.R


object AppRoutes {

    const val homeScreen = "homeScreen/"
    const val accountScreen = "accountScreen/"
    const val subscribtionScreen = "subscribtionScreen/"
    const val addAccountScreen = "addAccountScreen/"

}


sealed class Screen(
    val title: String,
    val route: String
) {


    sealed class DrawerScreen(
        val drawerTitle: String,
        val drawerRoute: String,
        @DrawableRes val icon: Int,
    ) : Screen(
        drawerTitle,
        drawerRoute
    ) {

        data object Account : DrawerScreen(
            "Account",
            AppRoutes.accountScreen,
            R.drawable.ic_account
        )

        data object Subscribiton : DrawerScreen(
            "Subscribiton",
            AppRoutes.subscribtionScreen,
            R.drawable.ic_sub
        )

        data object AddAccount : DrawerScreen(
            "Add Account",
            AppRoutes.addAccountScreen,
            R.drawable.ic_add
        )
    }


}