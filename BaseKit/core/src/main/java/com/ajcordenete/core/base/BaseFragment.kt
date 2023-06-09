package com.ajcordenete.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.addCallback
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.ajcordenete.core.R
import com.ajcordenete.core.ext.addMenuProvider

abstract class BaseFragment<B: ViewDataBinding> : Fragment() {

    lateinit var binding: B

    @LayoutRes
    abstract fun getLayoutId(): Int

    open fun attachToParent(): Boolean = false

    /**
     * Set to `true` when we want to exit app when user taps back or navigates up.
     * Usually used on auth screens before reaching home screen. Default is `false`.
     */
    open fun isExitOnBack(): Boolean = false

    @CallSuper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            getLayoutId(),
            container,
            attachToParent()
        )
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (isExitOnBack()) {
            requireActivity()
                .onBackPressedDispatcher
                .addCallback(viewLifecycleOwner) {
                    // Exit app.
                    requireActivity().finishAffinity()
                }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (canBack()) {
            if (item.itemId == android.R.id.home) {
                requireActivity().onBackPressedDispatcher.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun enableToolbarHomeIndicator(toolbar: Toolbar, title: String? = "") {
        (requireActivity() as AppCompatActivity).apply {
            setHasOptionsMenu(true)
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(canBack())
            supportActionBar?.setHomeButtonEnabled(canBack())

            val txtTitle = binding.root.findViewById<TextView>(R.id.txtToolbarCustomTitle)
            if (txtTitle != null) {
                supportActionBar?.title = ""
                txtTitle.text = title
            } else {
                supportActionBar?.title = title
            }
        }
    }

    fun setMenuOptions(@MenuRes menuRes: Int, onMenuSelectedItem: (id: Int) -> Boolean) {
        addMenuProvider(
            menuRes,
            onMenuSelectedItem
        )
    }

    /**
     * @return true if should use back button on toolbar
     */
    protected open fun canBack(): Boolean {
        return false
    }
}