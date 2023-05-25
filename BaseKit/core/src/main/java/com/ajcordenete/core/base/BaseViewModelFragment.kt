package com.ajcordenete.core.base

import androidx.databinding.ViewDataBinding

abstract class BaseViewModelFragment<B: ViewDataBinding, VM: BaseViewModel>: BaseFragment<B>() {}