package com.example.clicksource.firebase

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService

class MyFirebaseMessagingServices : FirebaseMessagingService() {

    override fun onNewToken(token : String){
        Log.d("tockenn","$token")

    }
}