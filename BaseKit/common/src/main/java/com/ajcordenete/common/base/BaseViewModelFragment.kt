package com.ajcordenete.common.base

import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels

abstract class BaseViewModelFragment<B: ViewDataBinding, VM: BaseViewModel>: BaseFragment<B>() {}