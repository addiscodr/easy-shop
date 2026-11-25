package com.addiscodr.easyshop

import android.content.Context
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore

object AppUtil {

    fun showToast(context: Context, message: String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun addToCart(productId: String, context: Context){

        val user_id = FirebaseAuth.getInstance().currentUser?.uid

        if(user_id == null){
            showToast(context, "Please login first")
            return
        }
        val user_doc = Firebase.firestore.collection(("users"))
            .document(user_id)
           // .document(FirebaseAuth.getInstance().currentUser?.uid!!)

        user_doc.get().addOnCompleteListener {task ->
            if(task.isSuccessful){
                val current_cart = task.result.get("cart_items") as? Map<String, Long> ?: emptyMap<String, Long>()
                val current_quantity = current_cart[productId]?:0L
                val updated_quantity = current_quantity + 1L

                val updated_cart = mapOf("cart_items.$productId" to updated_quantity)
                user_doc.update(updated_cart)
                    .addOnCompleteListener {updateTask ->
                        if(updateTask.isSuccessful){
                            showToast(context, "Item added to the Cart")
                        } else {
                            showToast(context, "Failed adding item to the Cart")

                        }
                    }
            } else {
                showToast(context, "Failed to retrieve Cart")
            }
        }

    }

    fun removeFromCart(productId: String, context: Context, removeAll : Boolean = false){

        val user_id = FirebaseAuth.getInstance().currentUser?.uid

        if(user_id == null){
            showToast(context, "Please login first")
            return
        }
        val user_doc = Firebase.firestore.collection(("users"))
            .document(user_id)
        // .document(FirebaseAuth.getInstance().currentUser?.uid!!)

        user_doc.get().addOnCompleteListener {task ->
            if(task.isSuccessful){
                val current_cart = task.result.get("cart_items") as? Map<String, Long> ?: emptyMap<String, Long>()
                val current_quantity = current_cart[productId]?:0L
                val updated_quantity = current_quantity - 1L

                val updated_cart =
                    if(updated_quantity <= 0 || removeAll){
                        mapOf("cart_items.$productId" to FieldValue.delete())
                    } else {
                        mapOf("cart_items.$productId" to updated_quantity)
                    }


                user_doc.update(updated_cart)
                    .addOnCompleteListener {updateTask ->
                        if(updateTask.isSuccessful){
                            showToast(context, "Item removed from the Cart")
                        } else {
                            showToast(context, "Failed removing item from the Cart")
                        }
                    }
            } else {
                showToast(context, "Failed to retrieve Cart")
            }
        }

    }

    fun getDiscountPercentage(): Float{
        return 5.0f
    }

    fun getTaxPercentage(): Float{
        return 15.0f
    }
}