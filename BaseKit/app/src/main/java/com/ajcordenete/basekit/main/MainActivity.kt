package com.ajcordenete.basekit.main

import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.ajcordenete.basekit.R
import com.ajcordenete.basekit.databinding.ActivityMainBinding
import com.ajcordenete.common.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getLayoutId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        // Enable support for Splash Screen API for
        // proper Android 12+ support
        installSplashScreen()

        super.onCreate(savedInstanceState)

        /*binding
            .bottomNavigation
            .setupWithNavController(
                findNavController(R.id.nav_host_fragment)
            )*/
    }
}