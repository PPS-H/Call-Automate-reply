package com.example.callautomatereply.Services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsManager
import android.telephony.TelephonyManager
import com.example.callautomatereply.model

class PhoneCallReceiver(var obj: model):BroadcastReceiver() {
    private var ring:Boolean=false
    private var callReceived:Boolean=false
    private var number:String?=null
    var state:String?=null
    override fun onReceive(context: Context?, intent: Intent?) {
        try{
            state=intent?.getStringExtra(TelephonyManager.EXTRA_STATE)
            if(state==null) {
                return
            }
            if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                ring=true
                number=intent?.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)
            }

            if(state.equals(TelephonyManager.CALL_STATE_OFFHOOK.toString())){
               callReceived=true
            }

            if(state.equals(TelephonyManager.EXTRA_STATE_IDLE)){
               if(ring && !callReceived){
                   ring=false
                   val smsManager=SmsManager.getDefault()
                   smsManager.sendTextMessage(number,null,obj.data,null,null)

               }
            }
            callReceived=false

        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}