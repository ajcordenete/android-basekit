package com.ajcordenete.basekit.home

import android.os.Bundle
import com.ajcordenete.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

): BaseViewModel() {

    override fun isFirstTimeUiCreate(bundle: Bundle?) {}
}