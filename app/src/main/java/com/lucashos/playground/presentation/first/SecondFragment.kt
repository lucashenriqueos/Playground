package com.lucashos.playground.presentation.first

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.lucashos.playground.R
import com.lucashos.playground.databinding.FragmentSecondFirstBinding
import com.lucashos.playground.presentation.second.SecondActivity

class SecondFragment : Fragment() {

    lateinit var binding: FragmentSecondFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_second_first, container, false
        )

        binding.button.setOnClickListener {
            fragmentManager?.beginTransaction()?.remove(this)?.commit()
            startActivityForResult(Intent(context, SecondActivity::class.java), 99)
        }

        return binding.root
    }
}