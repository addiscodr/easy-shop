package com.addiscodr.easyshop

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.addiscodr.easyshop.components.ProductDetailsView
import com.addiscodr.easyshop.pages.CategoryProductsPage
//import com.addiscodr.easyshop.pages.ProductDetailsPage
import com.addiscodr.easyshop.screens.AuthScreen
import com.addiscodr.easyshop.screens.HomeScreen
import com.addiscodr.easyshop.screens.LoginScreen
import com.addiscodr.easyshop.screens.SignupScreen
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun AppNavigation(modifier: Modifier = Modifier){

    val navController = rememberNavController()
    GlobalNavigation.navController = navController

    val isLoggedIn = Firebase.auth.currentUser != null

    val firstPage = if(isLoggedIn) "home" else "auth"

    NavHost(
        navController = navController,
        startDestination = firstPage
    ){
        composable(route = "auth"){
            AuthScreen(modifier, navController)
        }
        composable(route = "login"){
            LoginScreen(modifier, navController)
        }
        composable(route = "signup"){
            SignupScreen(modifier,navController)
        }
        composable(route = "home"){
            HomeScreen(modifier, navController)
        }
        composable(route = "category-products/{categoryId}"){
            val categoryId = it.arguments?.getString("categoryId")
            CategoryProductsPage(modifier, categoryId?:"")
        }
        composable(route = "product-details/{productId}"){
            val productId = it.arguments?.getString("productId")
            ProductDetailsView(modifier, productId?:"")
        }


    }

}

object GlobalNavigation {
    lateinit var navController: NavHostController
}