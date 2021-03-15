package com.sl.databindingdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.sl.databindingdemo.bean.User
import com.sl.databindingdemo.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mBinding = DataBindingUtil.setContentView<ActivityUserBinding>(this, R.layout.activity_user)

        mBinding.user = User("Songlin", 30)
        //mBinding.setVariable(BR.user, User("LL", 26))
    }
}