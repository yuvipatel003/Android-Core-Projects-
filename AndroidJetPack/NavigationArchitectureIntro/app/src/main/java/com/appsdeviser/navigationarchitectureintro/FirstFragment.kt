package com.appsdeviser.navigationarchitectureintro

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.appsdeviser.navigationarchitectureintro.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private lateinit var homeFragmentBinding : FragmentFirstBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homeFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_first, container, false)

        homeFragmentBinding.btnSubmit.setOnClickListener {
            if(!TextUtils.isEmpty(homeFragmentBinding.editText.text.toString())) {
            val bundle : Bundle = bundleOf("userName" to homeFragmentBinding.editText.text.toString())
            it.findNavController().navigate(R.id.action_homeFragment_to_secondFragment, bundle)
            } else {
                Toast.makeText(activity, "Please insert name",Toast.LENGTH_SHORT).show()
            }
        }
        return homeFragmentBinding.root
    }

}