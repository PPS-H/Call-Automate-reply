package com.example.callautomatereply

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class model (
    var state:String?=null,
    var data:String?=null
):Parcelable{

}