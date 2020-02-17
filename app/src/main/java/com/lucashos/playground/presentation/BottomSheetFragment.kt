package com.lucashos.playground.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.lucashos.playground.R
import kotlinx.android.synthetic.main.fragment_bottom_sheet.*

class BottomSheetFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomSheetButton.setOnClickListener(toast())

        scroll.setOnScrollChangeListener { _, scrollX, scrollY, _, _ ->

        }

        botaoteste.setOnClickListener(toast("Invisivel"))
    }

    private fun toast(message: String = "Clicou"): (View) -> Unit {
        return {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}