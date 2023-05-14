package com.ajcordenete.common.ext

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.annotation.MenuRes
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle

fun Fragment.addMenuProvider(@MenuRes menuRes: Int, callback: (id: Int) -> Boolean) {
    val menuProvider = object : MenuProvider {
        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            menuInflater.inflate(menuRes, menu)
        }

        override fun onMenuItemSelected(menuItem: MenuItem) = callback(menuItem.itemId)

    }
    (requireActivity() as MenuHost).addMenuProvider(
        menuProvider,
        viewLifecycleOwner,
        Lifecycle.State.RESUMED
    )
}