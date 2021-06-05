package com.example.mvvm_concept

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

internal class FragmentA : Fragment() {

    val TAG = "TAG_FragmentA"
    var data_tv: TextView? = null
    lateinit var userViewModel :UserViewModel


    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_a, parent, false)
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        // Setup any handles to view objects here
          data_tv = view.findViewById(R.id.data_tv);
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        /*Initializa Viewmodel*/
        /*Pass requireActivity() in fragment otherwise it will create another viewModel for the fragment*/
        userViewModel =
            ViewModelProviders.of(requireActivity()).get(UserViewModel::class.java)


        /*Observe live data
        * if live data changes, then observe callBack will receive updated User model */
        /*In fragment, pass viewLifecycleOwner.
       * It will solve issue of Leaking LiveData observers in Fragments*/
        userViewModel.usersData.observe(viewLifecycleOwner, Observer {
            /*Set data to view*/
            if(it!=null){
                data_tv?.text=it.username +
                        "\n"+it.gender+
                        "\n"+it.email+
                        "\n"+it.contact+
                        "\n"+it.address
            }
        })

        Log.d(TAG, "userViewModel in fragment: "+userViewModel)
    }
}








