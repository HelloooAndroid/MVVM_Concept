package com.example.mvvm_concept

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

class UserActivity : AppCompatActivity() {

    val TAG = "TAG_UserActivity"
    lateinit var userViewModel :UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        supportActionBar?.setTitle("UserActivity")


        var userDataTV :TextView= findViewById(R.id.userData);
        var next :TextView= findViewById(R.id.next);
        next.setOnClickListener {
            startActivity(Intent(this,ProfileActivity::class.java))
        }

        /*Initializa Viewmodel*/
        /*Pass requireActivity() in fragment otherwise it will create another viewModel for the fragment*/
        userViewModel =
            ViewModelProviders.of(this).get(UserViewModel::class.java)


        /*Observe live data
        * iflive data changes, then observe callBack will receive updated User model */
        /*In fragment, pass viewLifecycleOwner.
      * It will solve issue of Leaking LiveData observers in Fragments*/
        userViewModel.usersData.observe(this, Observer {
            /*Set data to view*/
            if(it!=null){
                userDataTV.text=it.username +
                        "\n"+it.gender+
                        "\n"+it.email+
                        "\n"+it.contact+
                        "\n"+it.address
            }

        })

        Handler().postDelayed(Runnable {
            userViewModel.getUserdata()
        },3000)


        Log.d(TAG, "userViewModel in Activity: "+userViewModel)

    }
}
