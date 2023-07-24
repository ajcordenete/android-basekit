package com.ajcordenete.authentication.register

import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.fragment.findNavController
import com.ajcordenete.authentication.R
import com.ajcordenete.authentication.databinding.FragmentRegisterBinding
import com.ajcordenete.authentication.login.LoginUiState
import com.ajcordenete.core.base.BaseFragment
import com.ajcordenete.core.ext.gone
import com.ajcordenete.core.ext.navigate
import com.ajcordenete.core.ext.ninjaTap
import com.ajcordenete.core.ext.visible
import com.ajcordenete.core.utils.AppRoutes
import com.ajcordenete.core.utils.ViewUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class RegisterFragment: BaseFragment<FragmentRegisterBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_register

    override fun canBack(): Boolean = true

    private val viewModel by viewModels<RegisterViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {setUpToolbar()
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
            .btnRegister
            .ninjaTap {
                resetErrors()
                viewModel.register(
                    binding.inputName.text.toString(),
                    binding.inputEmail.text.toString(),
                    binding.inputPassword.text.toString(),
                    binding.inputConfirmPassword.text.toString(),
                )
            }
            .launchIn(lifecycleScope)

        binding
            .txtLoginNow
            .ninjaTap {
                navigateToLogin()
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

    private fun handleState(state: RegisterUiState) {
        when(state) {
            is RegisterUiState.RegisterSuccessful -> {
                navigateToHome()
            }
            is RegisterUiState.InvalidName -> {
                binding.inputLayoutName.error = getString(R.string.invalid_name)
            }
            is RegisterUiState.InvalidEmail -> {
                binding.inputLayoutEmail.error = getString(com.ajcordenete.core.R.string.invalid_email)
            }
            is RegisterUiState.InvalidPassword-> {
                binding.inputLayoutPassword.error = getString(com.ajcordenete.core.R.string.invalid_password)
            }
            is RegisterUiState.PasswordsDoesNotMatch -> {
                binding.inputLayoutConfirmPassword.error = getString(R.string.invalid_match_password)
            }
            is RegisterUiState.ShowLoading -> {
                binding.loading.visible()
            }
            is RegisterUiState.HideLoading -> {
                binding.loading.gone()
            }
            is RegisterUiState.ShowError -> {
                ViewUtils.showGenericErrorSnackBar(binding.root, state.message)
            }
        }
    }

    private fun resetErrors() {
        binding.inputLayoutName.error = ""
        binding.inputLayoutEmail.error = ""
        binding.inputLayoutPassword.error = ""
        binding.inputLayoutConfirmPassword.error = ""
    }

    private fun navigateToHome() {
        val request = NavDeepLinkRequest.Builder
            .fromUri(AppRoutes.Main.Deeplink.HOME)
            .build()
        findNavController()
            .navigate(
                request,
                NavOptions
                    .Builder()
                    .setPopUpTo(
                        R.id.authNav,
                        true
                    )
                    .build()
            )
    }

    private fun navigateToLogin() {
        navigate(
            RegisterFragmentDirections
                .actionRegisterFragmentToLoginFragment()
        )
    }
}