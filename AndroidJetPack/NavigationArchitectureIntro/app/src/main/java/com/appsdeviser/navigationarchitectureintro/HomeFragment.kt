package com.appsdeviser.navigationarchitectureintro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.appsdeviser.navigationarchitectureintro.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeFragmentBinding : FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        homeFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        homeFragmentBinding.btnSignUp.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment2_to_firstFragment)
        }

        homeFragmentBinding.btnTerms.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment2_to_termsFragment)
        }

        return homeFragmentBinding.root
    }

}