package com.example.tranning_qr_scanner.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.tranning_qr_scanner.R
import com.example.tranning_qr_scanner.core.utils.TransitionType

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel> : Fragment() {
    protected lateinit var binding: VB
    protected lateinit var viewModel: VM

    open val viewModelClass: Class<VM>? = null
    open val transitionType: TransitionType = TransitionType.RTL

    protected lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflateLayout()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelClass?.let {
            viewModel = ViewModelProvider(this)[viewModelClass!!]
        }

        navController = findNavController()
        if (navController.currentDestination?.id == R.id.homeFragment) {
            navController = childFragmentManager.findFragmentById(R.id.homeFrag_body)!!.findNavController()
        }

        bind()
    }

    abstract fun bind()

    abstract fun inflateLayout(): VB

    fun navigate(id: Int, replace: Boolean = false, args: Bundle? = null) {
        val builder = NavOptions.Builder().apply {
            if (replace) setPopUpTo(navController.currentDestination!!.id, false)
            setEnterAnim(getEnterAnim())
            setExitAnim(0)
            setPopEnterAnim(getExitAnim())
            setPopExitAnim(0)
        }

        navController.navigate(
            id,
            args,
            builder.build()
        )
    }

    fun navigate(action: NavDirections, replace: Boolean = false) {
        val builder = NavOptions.Builder().apply {
            if (replace) setPopUpTo(navController.currentDestination!!.id, false)
            setEnterAnim(getEnterAnim())
            setExitAnim(0)
            setPopEnterAnim(getExitAnim())
            setPopExitAnim(0)
        }

        navController.navigate(
            action,
            builder.build()
        )
    }

    private fun getEnterAnim(): Int {
        return when (transitionType) {
            TransitionType.RTL -> R.anim.slide_rtl_in
            TransitionType.LTR -> R.anim.slide_ltr_in
            TransitionType.FADE -> R.anim.fade_in
            else -> 0
        }
    }

    private fun getExitAnim(): Int {
        return when (transitionType) {
            TransitionType.RTL -> R.anim.slide_rtl_out
            TransitionType.LTR -> R.anim.slide_ltr_out
            TransitionType.FADE -> R.anim.fade_out
            else -> 0
        }
    }

}
