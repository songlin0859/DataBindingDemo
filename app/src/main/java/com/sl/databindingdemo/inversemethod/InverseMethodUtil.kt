package com.sl.databindingdemo.inversemethod

import androidx.databinding.InverseMethod
import java.lang.Exception

@InverseMethod("stringToInt")
fun intToString(i: Int?): String? {
    return i?.toString()
}

fun stringToInt(str: String?): Int? {
    if (str == null){
        return null
    }
    try {
        return str.toInt()
    }catch (e : Exception){

    }
    return null

}
