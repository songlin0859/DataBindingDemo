package com.sl.databindingdemo.bean

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.sl.databindingdemo.BR

class TextAndSizeBeam : BaseObservable() {
    var textAndSize: String = "textAndSize50*20"
        @Bindable
        get
        set(value) {
            field = value
            notifyPropertyChanged(BR.textAndSize)
        }
}