package com.ajcordenete.basekit

import android.os.Bundle
import com.ajcordenete.basekit.databinding.ActivityMainBinding
import com.ajcordenete.common.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getLayoutId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}