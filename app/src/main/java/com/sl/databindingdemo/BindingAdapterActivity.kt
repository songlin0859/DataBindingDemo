package com.sl.databindingdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.sl.databindingdemo.databinding.ActivityBindingAdapterBinding

class BindingAdapterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_binding_adapter)

        val binding = DataBindingUtil.setContentView<ActivityBindingAdapterBinding>(
            this,
            R.layout.activity_binding_adapter
        )

        binding.textAndColor = "textAndColor*#FF0000"
    }
}