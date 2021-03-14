package com.sl.databindingdemo.anno

import androidx.databinding.BindingConversion

@BindingConversion
fun doubleToString(d: Double?): String {
    return d?.toString() ?: ""
}