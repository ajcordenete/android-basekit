package com.ajcordenete.authentication.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ajcordenete.authentication.R
import com.ajcordenete.authentication.databinding.FragmentLoginBinding
import com.ajcordenete.core.base.BaseFragment
import com.ajcordenete.core.ext.ninjaTap
import com.ajcordenete.core.utils.ViewUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class LoginFragment: BaseFragment<FragmentLoginBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_login
    override fun canBack(): Boolean = true

    private val viewModel by viewModels<LoginViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar()
        setUpVmObserver()
        setUpViews()
    }

    private fun setUpToolbar() {
        enableToolbarHomeIndicator(
            binding.toolbar.toolbarView,
            ""
        )
    }

    private fun setUpViews() {
        binding
            .btnLogin
            .ninjaTap {
                resetErrors()
                viewModel.login(
                    binding.inputUsername.text.toString(),
                    binding.inputPassword.text.toString()
                )
            }
            .launchIn(lifecycleScope)
    }

    private fun setUpVmObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel
                    .uiState
                    .collect(::handleState)
            }

        }
    }

    private fun handleState(state: LoginUiState) {
        when(state) {
            is LoginUiState.LoginSuccessful -> {
                ViewUtils.showGenericSuccessSnackBar(
                    binding.root,
                    getString(R.string.login)
                )
                Timber.i("login success")
            }
            is LoginUiState.InvalidEmail -> {
                binding.inputLayoutUsername.error = getString(com.ajcordenete.core.R.string.invalid_email)
            }
            is LoginUiState.InvalidPassword-> {
                binding.inputLayoutPassword.error = getString(com.ajcordenete.core.R.string.invalid_password)
            }
            is LoginUiState.ShowError -> {
                ViewUtils.showGenericErrorSnackBar(binding.root, state.message)
            }
        }
    }

    private fun resetErrors() {
        binding.inputLayoutUsername.error = ""
        binding.inputLayoutPassword.error = ""
    }
}