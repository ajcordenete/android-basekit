package com.ajcordenete.basekit.landing

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ajcordenete.basekit.MainGraphDirections
import com.ajcordenete.basekit.R
import com.ajcordenete.basekit.databinding.FragmentLandingBinding
import com.ajcordenete.core.base.BaseFragment
import com.ajcordenete.core.ext.navigate
import com.ajcordenete.core.utils.ViewUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LandingFragment: BaseFragment<FragmentLandingBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_landing

    private val viewModel by viewModels<LandingViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupVmObservers()

        viewModel.checkSession()
    }

    private fun setupVmObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel
                    .uiState
                    .collect(::handleState)
            }
        }
    }

    private fun handleState(state: LandingState) {
        when(state) {
            is LandingState.UserIsLoggedIn -> {
                navigate(
                    MainGraphDirections.actionGlobalToAuthGraph()
                )
            }
            is LandingState.UserIsNotLoggedIn -> {
                navigate(
                    MainGraphDirections.actionGlobalToAuthGraph()
                )
            }
            is LandingState.ShowError -> {
                ViewUtils.showGenericErrorSnackBar(
                    binding.root,
                    state.message
                )
            }
        }
    }
}