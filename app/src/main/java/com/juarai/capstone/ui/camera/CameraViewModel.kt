package com.juarai.capstone.ui.camera

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.juarai.capstone.data.network.UserResponse

class CameraViewModel: ViewModel() {

    val db = FirebaseFirestore.getInstance()
    val gson = Gson()

    fun addUsertoFirestore(user: UserResponse){
        val user = user.serializeToMap()
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d("addtoFirestore", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("addtoFirestore", "Error adding document", e)
            }
    }

    fun <T> T.serializeToMap(): Map<String, Any> {
        return convert()
    }

    inline fun <I, reified O> I.convert(): O {
        val json = gson.toJson(this)
        return gson.fromJson(json, object : TypeToken<O>() {}.type)
    }
}