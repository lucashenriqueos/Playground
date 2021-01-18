package com.lucashos.playground.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.lucashos.playground.R
import com.lucashos.playground.databinding.FragmentHomeBinding
import com.lucashos.playground.presentation.first.FirstActivity

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home, container, false
        )

        binding.btFlow.setOnClickListener {
            startActivity(Intent(context, FirstActivity::class.java))
        }

        return binding.root
    }

}