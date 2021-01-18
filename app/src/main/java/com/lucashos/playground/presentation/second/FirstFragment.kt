package com.lucashos.playground.presentation.second

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.lucashos.playground.R
import com.lucashos.playground.databinding.FragmentFirstSecondBinding

class FirstFragment : Fragment() {

    lateinit var binding: FragmentFirstSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_first_second, container, false
        )

        binding.btSecond.setOnClickListener {
            (activity as SecondActivity).goToFragment(SecondFragment())
        }

        binding.btClose.setOnClickListener {
            (activity as SecondActivity).run {
                setResult(Activity.RESULT_CANCELED)
                finish()
            }
        }

        return binding.root
    }
}