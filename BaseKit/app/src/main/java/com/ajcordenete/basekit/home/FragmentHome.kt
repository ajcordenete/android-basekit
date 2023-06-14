package com.ajcordenete.basekit.home

import android.os.Bundle
import android.view.View
import com.ajcordenete.basekit.R
import com.ajcordenete.core.R as commonR
import com.ajcordenete.basekit.databinding.FragmentHomeBinding
import com.ajcordenete.core.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ajcordenete.core.utils.ViewUtils
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class FragmentHome: BaseFragment<FragmentHomeBinding>() {

    private val viewModel by viewModels<HomeViewModel>()

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar()
        setUpVmObserver()

        viewModel.getUsers()
    }

    private fun setUpToolbar() {
        enableToolbarHomeIndicator(
            binding.toolbar.toolbarView,
            getString(commonR.string.home)
        )
    }

    private fun setUpVmObserver() {
        lifecycleScope.launch {
            viewModel
                .uiState
                .collect(::handleState)
        }
    }

    private fun handleState(state: HomeUiState) {
        when(state) {
            is HomeUiState.ShowUsers -> {
                Timber.i("users: ${state.users}")
            }
            is HomeUiState.ShowError -> {
                ViewUtils.showGenericErrorSnackBar(binding.root, state.message)
            }
        }
    }
}