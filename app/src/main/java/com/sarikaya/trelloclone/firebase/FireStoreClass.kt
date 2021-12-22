package com.sarikaya.trelloclone.firebase

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.sarikaya.trelloclone.model.User
import com.sarikaya.trelloclone.utils.Constants
import com.sarikaya.trelloclone.view.SignInActivity
import com.sarikaya.trelloclone.view.SignUpActivity

class FireStoreClass {

    private val mFireStore = FirebaseFirestore.getInstance()

    fun registerUser(activity : SignUpActivity, userInfo : User){
        mFireStore.collection(Constants.Users)
            .document(getCurrentUser()).set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                activity.userRegisteredSuccess()
            }.addOnFailureListener {
                e ->
                Log.e(activity.javaClass.simpleName,"Error writing documents")
            }
    }

    fun signInUser(activity : SignInActivity){
        mFireStore.collection(Constants.Users)
            .document(getCurrentUser()).get()
            .addOnSuccessListener { document ->
                val loggedInUser = document.toObject(User::class.java)
                if(loggedInUser != null)
                    activity.signInSuccess(loggedInUser)
            }.addOnFailureListener {
                    e ->
                Log.e("SignInUser","Error writing documents")
            }
    }

    fun getCurrentUser() : String{
        var currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserId = ""
        if(currentUser != null){
            currentUserId = currentUser.uid
        }
        return currentUserId
    }

}