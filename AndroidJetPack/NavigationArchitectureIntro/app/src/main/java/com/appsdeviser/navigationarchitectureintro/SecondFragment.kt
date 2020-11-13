package com.appsdeviser.navigationarchitectureintro

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.appsdeviser.navigationarchitectureintro.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {

    private lateinit var secondFragmentBinding : FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        secondFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_second, container, false)
        val userName = arguments!!.getString("userName")

        secondFragmentBinding.btnSubmit.setOnClickListener {
            if(!TextUtils.isEmpty(secondFragmentBinding.editTextEmail.text.toString())) {
                val bundle : Bundle = bundleOf("userName" to userName,
                    "userEmail" to secondFragmentBinding.editTextEmail.text.toString())
                it.findNavController().navigate(R.id.action_secondFragment_to_welcomeFragment, bundle)
            } else {
                Toast.makeText(activity, "Please insert email", Toast.LENGTH_SHORT).show()
            }
        }
        return secondFragmentBinding.root
    }

}