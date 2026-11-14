package com.addiscodr.easyshop

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.addiscodr.easyshop.screens.AuthScreen
import com.addiscodr.easyshop.screens.LoginScreen
import com.addiscodr.easyshop.screens.SignupScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "auth"
    ){
        composable(route = "auth"){
            AuthScreen(modifier, navController)
        }
        composable(route = "login"){
            LoginScreen(modifier)
        }
        composable(route = "signup"){
            SignupScreen(modifier)
        }




    }

}