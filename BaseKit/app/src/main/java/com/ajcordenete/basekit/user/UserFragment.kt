package com.ajcordenete.basekit.user

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ajcordenete.basekit.R
import com.ajcordenete.basekit.databinding.FragmentUserBinding
import com.ajcordenete.core.base.BaseFragment
import com.ajcordenete.core.utils.ViewUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class UserFragment: BaseFragment<FragmentUserBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_user

    private val viewModel by viewModels<UserViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar()
        setUpVmObserver()

        viewModel.getUsers()
    }

    private fun setUpToolbar() {
        enableToolbarHomeIndicator(
            binding.toolbar.toolbarView,
            getString(com.ajcordenete.core.R.string.user)
        )
    }

    private fun setUpVmObserver() {
        lifecycleScope.launch {
            viewModel
                .uiState
                .collect(::handleState)
        }
    }

    private fun handleState(state: UserUiState) {
        when(state) {
            is UserUiState.ShowUsers -> {
                Timber.i("users: ${state.users}")
            }
            is UserUiState.ShowError -> {
                ViewUtils.showGenericErrorSnackBar(binding.root, state.message)
            }
        }
    }
}