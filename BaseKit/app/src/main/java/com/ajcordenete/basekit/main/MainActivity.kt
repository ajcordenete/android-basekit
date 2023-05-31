package com.ajcordenete.basekit.main

import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ajcordenete.basekit.R
import com.ajcordenete.basekit.databinding.ActivityMainBinding
import com.ajcordenete.core.base.BaseActivity
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