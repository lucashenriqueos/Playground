package com.lucashos.playground.presentation.first

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.lucashos.playground.R
import com.lucashos.playground.databinding.FragmentFirstFirstBinding

class FirstFragment : Fragment() {

    lateinit var binding: FragmentFirstFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_first_first, container, false
        )

        binding.btSecond.setOnClickListener {
            (activity as FirstActivity).goToFragment(SecondFragment())
        }

        binding.btClose.setOnClickListener {
            (activity as FirstActivity).run {
                finish()
            }
        }

        return binding.root
    }
}