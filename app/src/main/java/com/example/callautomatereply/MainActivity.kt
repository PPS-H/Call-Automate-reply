package com.example.callautomatereply

import android.Manifest
import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.example.callautomatereply.Services.MyService
import com.example.callautomatereply.UI.HomeFragment
import com.example.callautomatereply.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    companion object{
        var shrd:SharedPreferences?=null
        var editor:SharedPreferences.Editor?=null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestpermissions()
        shrd=getSharedPreferences("service_stopped", MODE_PRIVATE)
        editor=shrd?.edit()

        if(intent.extras?.keySet()?.contains("startup_id") == true) {
            AlertDialog.Builder(this)
                    .setTitle("Stop Service!!")
                    .setMessage("Are you really want to stop the service")
                    .setPositiveButton("Accept"){ _ , _ ->
                        var intent=Intent(this,MyService::class.java)
                        stopService(intent)
                        editor?.putString("UISetter","true")
                        editor?.apply()
                        Toast.makeText(this,"Service Stopped Successfully!!",Toast.LENGTH_SHORT).show()
                    }
                    .setNegativeButton("Decline"){ _ , _ ->

                    }.create().show()
        }




        }
    private fun hasSendSMSPermission()=
            ActivityCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS)==PackageManager.PERMISSION_GRANTED
    private fun hasReadIncomingNumberPermission()=
            ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_PHONE_STATE)==PackageManager.PERMISSION_GRANTED

    private fun requestpermissions(){
        var permissions= mutableListOf<String>()
        if(!hasReadIncomingNumberPermission()){
            permissions.add(Manifest.permission.READ_PHONE_STATE)
        }
        if(!hasSendSMSPermission()){
            permissions.add(Manifest.permission.SEND_SMS)
        }
        if(permissions.isNotEmpty()){
            ActivityCompat.requestPermissions(this,permissions.toTypedArray(),0)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==0 && grantResults.isNotEmpty()){
            for(i in grantResults.indices){
                if(grantResults[i]==PackageManager.PERMISSION_GRANTED){

                }else{
//                    Toast.makeText(this,"Please allow the permission to work the app properly",Toast.LENGTH_LONG).show()
                    requestpermissions()
                }
            }

        }
    }
}

