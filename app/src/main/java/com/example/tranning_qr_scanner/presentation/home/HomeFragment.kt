package com.example.tranning_qr_scanner.presentation.home

import com.example.tranning_qr_scanner.R
import com.example.tranning_qr_scanner.databinding.HomeFragmentBinding
import com.example.tranning_qr_scanner.presentation.base.BaseFragment

class HomeFragment : BaseFragment<HomeFragmentBinding, Nothing>() {
    override fun inflateLayout() = HomeFragmentBinding.inflate(layoutInflater)

    override fun bind() {
        binding.homeFragNav.setOnItemSelectedListener { item ->
            if(item.itemId == navController.currentDestination?.id){
                when(item.itemId){
                    R.id.nav_scan -> navigate(R.id.scanQRFragment, true)
                    R.id.nav_create -> navigate(R.id.createQRFragment, true)
                    R.id.nav_history -> navigate(R.id.historyFragment, true)
                    R.id.nav_settings -> navigate(R.id.settingsFragment, true)
                }
            }
            true
        }
    }
}