package com.example.callautomatereply.Services

import android.telecom.Call
import android.telecom.CallScreeningService

class MyCallScreeningService:CallScreeningService() {
    companion object{
        var phone_number:String?=null
    }
    override fun onScreenCall(callDetails: Call.Details) {
        phone_number= callDetails.callProperties.toString()
    }
}