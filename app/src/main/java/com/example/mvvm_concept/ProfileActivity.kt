package com.example.mvvm_concept

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

class ProfileActivity : AppCompatActivity() {

    lateinit var userViewModel :UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        supportActionBar?.setTitle("ProfileActivity")

        var userDataTV : TextView = findViewById(R.id.userDataProfile)

        /*Initializa Viewmodel*/
        userViewModel =
            ViewModelProviders.of(this).get(UserViewModel::class.java)

        /*Observe live data
        * iflive data changes, then observe callBack will receive updated User model */
        userViewModel.usersData.observe(this, Observer {
            /*Set data to view*/
            if(it!=null){
                userDataTV.text=it.username +
                        "\n"+it.gender+
                        "\n"+it.email+
                        "\n"+it.contact+
                        "\n"+it.address
            }else{
                Toast.makeText(this,"No data available",Toast.LENGTH_LONG).show()
            }

        })

        userViewModel.setUserdata()


    }
}