package com.example.mvvm_concept

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel() : ViewModel() {

    private val TAG = "TAG_UserViewModel"
    var usersData = MutableLiveData<User>()

    private val feedRepo = UserRepo()

    init {
        // ViewModel is created only once during Activity/Fragment lifetime
        /*Data which need to be called once, then do not call in onCreate() of activity,
        instead of that, use init block of viewModel for the same*/
        usersData = feedRepo.usersData
        //feedRepo.getUserdata()   //Situation 1
    }

    fun getUserdata() {
        //Situation 2
        Log.d(TAG, "getUserdata: ")
        feedRepo.getUserdata()
    }

    fun setUserdata() {
        Log.d(TAG, "getUserdata: ")
        var user = User("Aniket P","Male","anky@gmail.com","9223456109","Mumbai")
        usersData.value=user
    }

    fun setFun() {
        //Situation 3
        if (usersData.value != null) {
            Log.d(TAG, "getUserdata: ")
            var user = User("Aniket P", "Male", "anky@gmail.com", "9223456109", "Mumbai")
            usersData.value = user
        }
    }
}