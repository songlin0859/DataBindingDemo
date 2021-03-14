package com.sl.databindingdemo.anno

import android.graphics.Color
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("textAndColor", requireAll = true)
fun setTextAndColor(view: TextView, textAndColor: String) {
    val split = textAndColor.split("*")
    view.text = split[0]
    view.setTextColor(Color.parseColor(split[1]))
}