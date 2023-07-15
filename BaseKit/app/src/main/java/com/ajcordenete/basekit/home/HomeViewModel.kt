package com.ajcordenete.basekit.home

import android.os.Bundle
import com.ajcordenete.core.base.BaseViewModel
import com.ajcordenete.core.ext.launch
import com.ajcordenete.data.feature.user.UserRepository
import com.ajcordenete.domain.error
import com.ajcordenete.domain.get
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

): BaseViewModel() {

    override fun isFirstTimeUiCreate(bundle: Bundle?) {}

}