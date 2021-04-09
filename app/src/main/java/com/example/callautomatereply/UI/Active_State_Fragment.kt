package com.example.callautomatereply.UI

import android.os.Bundle
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.callautomatereply.MainActivity
import com.example.callautomatereply.R
import com.example.callautomatereply.databinding.FragmentActiveStateBinding
import com.example.callautomatereply.model


class Active_State_Fragment : Fragment() {
    private var binding:FragmentActiveStateBinding?=null
    private var data:String?=null
    private var state:String?=null
    var clicked:Boolean=false
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding= FragmentActiveStateBinding.inflate(inflater,container,false)

        return binding!!.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding!!.card1.setOnClickListener {
            checked_or_not()
            binding!!.card1.isChecked = !binding!!.card1.isChecked
            state= binding!!.text1.text.toString()
            data="Studying,i will call you soon."
            clicked=true
        }

        binding!!.card2.setOnClickListener {
            checked_or_not()
            binding!!.card2.isChecked = !binding!!.card2.isChecked
            state= binding!!.text2.text.toString()
            data="Having fun in movie, dont't disturb,will call you later."
            clicked=true
        }

        binding!!.card3.setOnClickListener {
            checked_or_not()
            binding!!.card3.isChecked = !binding!!.card3.isChecked
            state= binding!!.text3.text.toString()
            data="At work, will call some time later."
            clicked=true
        }

        binding!!.card4.setOnClickListener {
            checked_or_not()
            binding!!.card4.isChecked = !binding!!.card4.isChecked
            state= binding!!.text4.text.toString()
            data="I am in Meeting, i will call you after it finishes."
            clicked=true
        }

        binding!!.card5.setOnClickListener {
            checked_or_not()
            binding!!.card5.isChecked = !binding!!.card5.isChecked
            state= binding!!.text5.text.toString()
            data="On travel, call you later."
            clicked=true
        }

        binding!!.card6.setOnClickListener {
            checked_or_not()
            binding!!.card6.isChecked = !binding!!.card6.isChecked
            state= binding!!.text6.text.toString()
            data="Driving, will call you later."
            clicked=true
        }

        binding!!.card7.setOnClickListener {
            checked_or_not()
            binding!!.card7.isChecked = !binding!!.card7.isChecked
            state= binding!!.text7.text.toString()
            data="On holiday, call you later."
            clicked=true
        }

        binding!!.card8.setOnClickListener {
            checked_or_not()
            binding!!.card8.isChecked = !binding!!.card8.isChecked
            state= binding!!.text8.text.toString()
            data="Low battery constraint, not able to talk"
            clicked=true
        }

        binding!!.card9.setOnClickListener {
            checked_or_not()
            binding!!.card9.isChecked = !binding!!.card9.isChecked
            state= binding!!.text9.text.toString()
            data="Not well, call you later."
            clicked=true
        }

        binding!!.card10.setOnClickListener {
            checked_or_not()
            binding!!.card10.isChecked = !binding!!.card10.isChecked
            state= binding!!.text10.text.toString()
            data="Busy, no calls please."
            clicked=true
        }

        binding!!.card11.setOnClickListener {
            val action=Active_State_FragmentDirections.customeMessage()
            Navigation.findNavController(it).navigate(action)
        }
        binding!!.continueButton.setOnClickListener{
            if(!clicked){
             Toast.makeText(context,"Please select any state",Toast.LENGTH_SHORT).show()
            }else {
                val obj = model(state, data)
                HomeFragment.editor?.putString("state",state)
                HomeFragment.editor?.apply()
                MainActivity.editor?.putString("UISetter","false")
                MainActivity.editor?.apply()
                val action = Active_State_FragmentDirections.backToHome(obj)
                Navigation.findNavController(it).navigate(action)


            }
        }
    }


    private fun checked_or_not() {
        val list= listOf(
            binding!!.card1,
            binding!!.card2,
            binding!!.card3,
            binding!!.card4,
            binding!!.card5,
            binding!!.card6,
            binding!!.card7,
            binding!!.card8,
            binding!!.card9,
            binding!!.card10)
        for (element in list){
            element.isChecked=false
        }
    }


}

