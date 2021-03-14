package com.sl.databindingdemo.anno

import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener

@BindingAdapter("textAndSize", requireAll = true)
fun setTextAndSize(view: TextView, textAndSize: String) {

    val oldTextAndSize = getTextAndSize(view)
    if (oldTextAndSize == textAndSize){
        return
    }

    //val split = textAndColor.split("*")
    //view.text = split[0]
    //view.textSize = split[1].toFloat()

    view.text = textAndSize
}

@InverseBindingAdapter(attribute = "textAndSize" /*, event = "textAndSizeAttrChanged"*/)
fun getTextAndSize(view: TextView): String {
    //return "${view.text}*${view.textSize}"
    return "${view.text}"
}

@BindingAdapter("textAndSizeAttrChanged")
fun setTextAndColorAttrChanged(view:TextView, listener:InverseBindingListener): Unit{
    view.addTextChangedListener(object :TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(p0: Editable?) {
            listener.onChange()
        }

    })
}

