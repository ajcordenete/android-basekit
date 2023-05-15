package com.ajcordenete.basekit.home

import android.os.Bundle
import android.view.View
import com.ajcordenete.basekit.R
import com.ajcordenete.common.R as commonR
import com.ajcordenete.basekit.databinding.FragmentHomeBinding
import com.ajcordenete.common.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels

@AndroidEntryPoint
class FragmentHome: BaseFragment<FragmentHomeBinding>() {

    private val viewModel by viewModels<HomeViewModel>()

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar()
    }

    private fun setUpToolbar() {
        enableToolbarHomeIndicator(
            binding.toolbar.toolbarView,
            getString(commonR.string.home)
        )
    }
}