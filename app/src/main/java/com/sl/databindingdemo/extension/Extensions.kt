package com.sl.databindingdemo.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast

fun Context.toast(toast:String){
    Toast.makeText(this, toast, Toast.LENGTH_SHORT).show()
}