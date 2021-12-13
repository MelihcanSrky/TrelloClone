package com.sarikaya.trelloclone.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.sarikaya.trelloclone.model.User
import com.sarikaya.trelloclone.utils.Constants
import com.sarikaya.trelloclone.view.SignUpActivity

class FireStoreClass {

    private val mFireStore = FirebaseFirestore.getInstance()

    fun registerUser(activity : SignUpActivity, userInfo : User){
        mFireStore.collection(Constants.Users)
            .document(getCurrentUser()).set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                activity.userRegisteredSuccess()
            }
    }

    fun getCurrentUser() : String{
        return  FirebaseAuth.getInstance().currentUser!!.uid
    }

}