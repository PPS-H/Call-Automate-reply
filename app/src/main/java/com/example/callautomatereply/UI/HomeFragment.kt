package com.example.callautomatereply.UI

import android.content.Context
import android.content.Intent
import android.content.Intent.getIntent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.callautomatereply.MainActivity
import com.example.callautomatereply.Services.PhoneCallReceiver
import com.example.callautomatereply.R
import com.example.callautomatereply.Services.MyService
import com.example.callautomatereply.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private var binding:FragmentHomeBinding?=null
    private val args by navArgs<HomeFragmentArgs>()
    companion object{
         var shrd:SharedPreferences?=null
         var editor:SharedPreferences.Editor?=null
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding= FragmentHomeBinding.inflate(inflater,container,false)
        return binding!!.root
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        shrd=context?.getSharedPreferences("savedInfo", Context.MODE_PRIVATE)
        editor=shrd?.edit()

        val msg=args.currentState
        if(msg!=null){
            Intent(context, MyService::class.java).also{
                it.putExtra("data",msg.data)
                it.putExtra("state",msg.state)
                context?.startService(it)
            }

            //Update text and image
            binding!!.CurrentState.text = msg.state
            when (args.currentState?.state) {
                "Study" -> binding!!.currentImage.setImageResource(R.drawable.studying)
                "Watching Movie" -> binding!!.currentImage.setImageResource(R.drawable.movies)
                "At Work" -> binding!!.currentImage.setImageResource(R.drawable.working)
                "Meeting" -> binding!!.currentImage.setImageResource(R.drawable.conversation)
                "Travelling" -> binding!!.currentImage.setImageResource(R.drawable.airplane)
                "Driving" -> binding!!.currentImage.setImageResource(R.drawable.hands)
                "Holiday" -> binding!!.currentImage.setImageResource(R.drawable.holiday)
                "Battery Low" -> binding!!.currentImage.setImageResource(R.drawable.battery_low)
                "Not Well" -> binding!!.currentImage.setImageResource(R.drawable.vitamin)
                "No Call" -> binding!!.currentImage.setImageResource(R.drawable.no_call)
                "Message Saved!" -> binding!!.currentImage.setImageResource(R.drawable.approved)
            }
        }else {
            if (MainActivity.shrd?.getString("UISetter","false")=="true") {
                binding!!.currentImage.setImageResource(R.drawable.message)
                binding!!.CurrentState.text = "Undefined"
            } else {

                var shrdTxt = shrd?.getString("state", "Not Saved")
                if (shrdTxt != "Not Saved") {
                    binding?.CurrentState?.text = shrdTxt
                    when (shrdTxt.toString()) {
                        "Study" -> binding!!.currentImage.setImageResource(R.drawable.studying)
                        "Watching Movie" -> binding!!.currentImage.setImageResource(R.drawable.movies)
                        "At Work" -> binding!!.currentImage.setImageResource(R.drawable.working)
                        "Meeting" -> binding!!.currentImage.setImageResource(R.drawable.conversation)
                        "Travelling" -> binding!!.currentImage.setImageResource(R.drawable.airplane)
                        "Driving" -> binding!!.currentImage.setImageResource(R.drawable.hands)
                        "Holiday" -> binding!!.currentImage.setImageResource(R.drawable.holiday)
                        "Battery Low" -> binding!!.currentImage.setImageResource(R.drawable.battery_low)
                        "Not Well" -> binding!!.currentImage.setImageResource(R.drawable.vitamin)
                        "No Call" -> binding!!.currentImage.setImageResource(R.drawable.no_call)
                        "Message Saved!" -> binding!!.currentImage.setImageResource(R.drawable.approved)
                    }
                }
            }
        }

        binding!!.button.setOnClickListener{
        val action=HomeFragmentDirections.selectState()
        Navigation.findNavController(it).navigate(action)
        }
    }


}