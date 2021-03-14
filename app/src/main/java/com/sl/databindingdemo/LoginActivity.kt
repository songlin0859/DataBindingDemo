package com.sl.databindingdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.sl.databindingdemo.bean.LoginBean
import com.sl.databindingdemo.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var login: LoginBean
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val contentView =
            DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
        login = LoginBean()
        contentView.login = login;
    }
}