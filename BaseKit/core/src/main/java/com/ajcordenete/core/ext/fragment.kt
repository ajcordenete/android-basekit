package com.ajcordenete.core.ext

import android.os.Environment
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.annotation.MenuRes
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.IOException

fun Fragment.isDestinationInBackstack(@IdRes destinationId: Int): Boolean {
    return try {
        findNavController().getBackStackEntry(destinationId)
        true
    } catch (e: Exception) {
        false
    }
}

@Throws(IOException::class)
fun Fragment.createImageFile(): File {
    val storageDir: File = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!

    return File.createTempFile(
        "avatar", /* prefix */
        ".jpg", /* suffix */
        storageDir /* directory */
    )
}

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

fun Fragment.navigate(dest: NavDirections, navigatorExtras: Navigator.Extras? = null) {
    if (navigatorExtras != null) {
        findNavController()
            .navigate(
                dest,
                navigatorExtras
            )
    } else {
        findNavController().navigate(dest)
    }
}

fun Fragment.navigate(dest: NavDirections, navOptions: NavOptions) {
    findNavController()
        .navigate(
            dest,
            navOptions
        )
}

fun Fragment.navigateUp() = findNavController().navigateUp()

/**
 * Launches [action] inside `viewLifecycleOwner.lifecycleScope`.
 */
fun Fragment.launch(action: suspend CoroutineScope.() -> Unit) {
    viewLifecycleOwner.lifecycleScope.launch {
        action(this)
    }
}

/**
 * Launches [action] inside `viewLifecycleOwner.lifecycleScope` when lifecycle is [Lifecycle.State.STARTED].
 */
fun Fragment.launchWhenStarted(action: suspend CoroutineScope.() -> Unit) {
    viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        action(this)
    }
}

/**
 * Launches [action] inside `viewLifecycleOwner.lifecycleScope` when lifecycle is [Lifecycle.State.RESUMED].
 */
fun Fragment.launchWhenResumed(action: suspend CoroutineScope.() -> Unit) {
    viewLifecycleOwner.lifecycleScope.launchWhenResumed {
        action(this)
    }
}

/**
 * Launches [action] inside `viewLifecycleOwner.lifecycleScope` when lifecycle is [Lifecycle.State.CREATED].
 */
fun Fragment.launchWhenCreated(action: suspend CoroutineScope.() -> Unit) {
    viewLifecycleOwner.lifecycleScope.launchWhenCreated {
        action(this)
    }
}