package com.example.mvvm_concept

import android.util.Log
import androidx.lifecycle.MutableLiveData

class UserRepo()  {
    private val TAG = "TAG_UserRepo"

    var usersData = MutableLiveData<User>()


    fun getUserdata() {
        Log.d(TAG, "getUserdata: ")

        /*Make NW call here to get data*/
        /*instead, m generating data here only!*/
        Thread.sleep(2000)
        var user = User("Pratim P","Male","pratim@gmail.com","9223456108","Navi mumbai")
        usersData.value=user


    }
}

