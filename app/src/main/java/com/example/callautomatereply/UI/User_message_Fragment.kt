package com.example.callautomatereply.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.callautomatereply.MainActivity
import com.example.callautomatereply.databinding.FragmentHomeBinding
import com.example.callautomatereply.databinding.FragmentUserMessageBinding
import com.example.callautomatereply.model


class User_message_Fragment : Fragment() {
    var data:String?=null
    var binding:FragmentUserMessageBinding?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding= FragmentUserMessageBinding.inflate(inflater,container,false)
        return binding!!.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding?.send?.setOnClickListener{
            data=binding?.usersMessage?.text.toString()
            val obj=model("Message Saved!",data)
            HomeFragment.editor?.putString("state","Message Saved!")
            HomeFragment.editor?.apply()
            MainActivity.editor?.putString("UISetter","false")
            MainActivity.editor?.apply()
            val action=User_message_FragmentDirections.backToHome(obj)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

    }
}

