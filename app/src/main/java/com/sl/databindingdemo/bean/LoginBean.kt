package com.sl.databindingdemo.bean

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.sl.databindingdemo.BR

class LoginBean : BaseObservable() {
    @Bindable
    var name: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }

    var password: String? = null
        @Bindable
        get
        set(value) {
            field = value
            notifyPropertyChanged(BR.password)
        }

    @get:Bindable
    var age: Int? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.age)
        }

    @Bindable(value = ["name", "password", "age"])
    var desc: String? = null
        get() {
            return "${name ?: ""}:${password ?: ""}:age=${age ?: ""}"
        }
        private set


}