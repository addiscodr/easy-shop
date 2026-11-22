package com.addiscodr.easyshop.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.addiscodr.easyshop.components.CartItemView
import com.addiscodr.easyshop.model.UserModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

@Composable
fun CartPage(){

    val user_model = remember {
        mutableStateOf(UserModel())
    }

    LaunchedEffect(Unit) {
        Firebase.firestore.collection("users")
            .document(FirebaseAuth.getInstance().currentUser?.uid!!)
            .get().addOnCompleteListener {task ->
                if(task.isSuccessful){
                    val result = task.result.toObject(UserModel::class.java)
                    if(result!=null){
                        user_model.value = result
                    }
                }
            }
    }
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Your cart", style = TextStyle(
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold
        ))

        LazyColumn() {
            items(user_model.value.cart_items.toList()){(productId, qty) ->

                CartItemView(productId = productId, qty = qty)
            }
        }
    }
}