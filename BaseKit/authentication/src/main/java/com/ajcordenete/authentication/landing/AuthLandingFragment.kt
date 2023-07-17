package com.ajcordenete.authentication.landing

import com.ajcordenete.authentication.R
import com.ajcordenete.authentication.databinding.FragmentAuthLandingBinding
import com.ajcordenete.core.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthLandingFragment: BaseFragment<FragmentAuthLandingBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_auth_landing
}