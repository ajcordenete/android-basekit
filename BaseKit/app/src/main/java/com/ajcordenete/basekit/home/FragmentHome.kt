package com.ajcordenete.basekit.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.ajcordenete.basekit.R
import com.ajcordenete.basekit.databinding.FragmentHomeBinding
import com.ajcordenete.core.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import com.ajcordenete.core.R as commonR

@AndroidEntryPoint
class FragmentHome: BaseFragment<FragmentHomeBinding>() {

    private val viewModel by viewModels<HomeViewModel>()

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar()
        viewModel.register()
    }

    private fun setUpToolbar() {
        enableToolbarHomeIndicator(
            binding.toolbar.toolbarView,
            getString(commonR.string.home)
        )
    }
}