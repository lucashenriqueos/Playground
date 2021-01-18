package com.lucashos.playground.presentation.second

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.lucashos.playground.R
import com.lucashos.playground.databinding.FragmentSecondSecondBinding

class SecondFragment : Fragment() {

    lateinit var binding: FragmentSecondSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_second_second, container, false
        )

        binding.button.setOnClickListener {
            (activity as SecondActivity).run {
                startActivity(Intent(this, SecondActivity::class.java))
                setResult(Activity.RESULT_OK)
                finish()
            }
        }

        return binding.root
    }
}