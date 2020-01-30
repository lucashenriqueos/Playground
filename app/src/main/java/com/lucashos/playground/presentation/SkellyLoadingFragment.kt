package com.lucashos.playground.presentation

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.lucashos.playground.R
import com.lucashos.playground.databinding.FragmentSkellyLoadingBinding
import kotlinx.android.synthetic.main.fragment_skelly_loading.*

class SkellyLoadingFragment : Fragment() {

    private lateinit var binding: FragmentSkellyLoadingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater,
                R.layout.fragment_skelly_loading, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        showLoading()
        Handler().postDelayed({
            hideLoading()
        }, 2000)
    }

    private fun hideLoading() {
        shimmer_view_container.stopShimmer()
        shimmer_view_container.isVisible = false
        textView.text = getEmoji(getString(R.string.euro_emoji))
        textView.isVisible = true
    }

    private fun showLoading() {
        shimmer_view_container.startShimmer()
        shimmer_view_container.isVisible = true
        textView.isVisible = false
    }

    private fun getEmoji(unicode: String): String {
        return String(Character.toChars(unicode.toInt()))
    }

    companion object {
        const val EMOJI = 0x1F4B6
    }
}