package com.addiscodr.easyshop.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.addiscodr.easyshop.AppUtil
import com.addiscodr.easyshop.R
import com.addiscodr.easyshop.viewmodel.AuthViewModel

@Composable
fun LoginScreen (modifier: Modifier,navController: NavController,  authViewModel: AuthViewModel = viewModel()){

    var email by remember {
        mutableStateOf(value = "")
    }

    var password by remember {
        mutableStateOf(value = "")
    }

    var isLoading by remember {
        mutableStateOf(value = false)
    }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome back!", modifier = Modifier.fillMaxWidth(), style = TextStyle(
                fontSize = 30.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
            )
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Sign in to your account", modifier = Modifier.fillMaxWidth(), style = TextStyle(
                fontSize = 21.sp,
            )
        )
        Spacer(modifier = Modifier.height(20.dp))

        Image(
            painter = painterResource(R.drawable.auth),
            contentDescription = "Login Banner",
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp))

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text(text = "Email")
            },
            modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text(text = "Password")
            },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation())

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

                if (email.isBlank() || password.isBlank()) {
                    AppUtil.showToast(
                        context,
                        "Please enter email and password!"
                    )
                    return@Button
                }

                isLoading = true

                authViewModel.login(
                    email.trim(),
                    password
                ) { success, errorMessage ->

                    isLoading = false

                    if (success) {

                        navController.navigate("home") {
                            popUpTo("auth") {
                                inclusive = true
                            }
                        }

                    } else {

                        AppUtil.showToast(
                            context,
                            errorMessage ?: "Login failed!"
                        )
                    }
                }
            },
            enabled = !isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Text(
                text = if (isLoading) "Logging in..." else "Login",
                fontSize = 21.sp
            )
        }



    }

}