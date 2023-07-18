package com.ajcordenete.authentication.landing

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.ajcordenete.authentication.R
import com.ajcordenete.authentication.databinding.FragmentAuthLandingBinding
import com.ajcordenete.core.base.BaseFragment
import com.ajcordenete.core.ext.navigate
import com.ajcordenete.core.ext.ninjaTap
import kotlinx.coroutines.flow.launchIn

class AuthLandingFragment: BaseFragment<FragmentAuthLandingBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_auth_landing

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

    private fun setUpViews() {
        binding
            .btnLogin
            .ninjaTap {
                navigate(
                    AuthLandingFragmentDirections
                        .actionAuthLandingFragmentToLoginFragment()
                )
            }
            .launchIn(lifecycleScope)

        binding
            .btnRegister
            .ninjaTap {

            }
            .launchIn(lifecycleScope)
    }
}