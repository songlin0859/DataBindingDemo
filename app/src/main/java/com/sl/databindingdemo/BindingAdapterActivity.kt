package com.sl.databindingdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import com.sl.databindingdemo.bean.TextAndSizeBeam
import com.sl.databindingdemo.databinding.ActivityBindingAdapterBinding

class BindingAdapterActivity : AppCompatActivity() {
    private lateinit var textAndSize: TextAndSizeBeam
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_binding_adapter)

        val binding = DataBindingUtil.setContentView<ActivityBindingAdapterBinding>(
            this,
            R.layout.activity_binding_adapter
        )

        textAndSize = TextAndSizeBeam()

        binding.textAndColor = "textAndColor*#FF0000"
        binding.textAndSize = textAndSize
    }
}