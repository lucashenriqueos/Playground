package com.lucashos.playground.presentation

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.lucashos.playground.R
import com.lucashos.playground.databinding.FragmentOverlapBinding
import kotlin.math.abs

class OverlapFragment : Fragment() {

    lateinit var binding: FragmentOverlapBinding
    var backgroundViewHeight = 0
    var originalTextSize = 0f

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_overlap, container, false
        )
        resizeScrollView()
//        addFadeOutOnScrolling()
        addTextResizeOnScrolling()
        return binding.root
    }

    private fun addFadeOutOnScrolling() {
        binding.scroll.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            fadeView(oldScrollY - scrollY, binding.background)
        }
    }

    private fun addTextResizeOnScrolling() {
        binding.scroll.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            resizeText(oldScrollY - scrollY, binding.background)
        }
    }

    private fun resizeScrollView() {
        binding.root.viewTreeObserver.addOnGlobalLayoutListener {
            val layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
            if (backgroundViewHeight == 0) backgroundViewHeight = binding.background.height
            layoutParams.topMargin = backgroundViewHeight
            binding.scrollContent.layoutParams = layoutParams
        }
    }

    private fun fadeView(scrollValue: Int, view: View) {
        val fadingBias = 1 / backgroundViewHeight.toFloat()
        var alpha = abs(view.alpha + scrollValue * fadingBias)
        if (alpha < 0) alpha = 0f
        if (alpha > 1) alpha = 1f
        view.alpha = alpha
    }

    private fun resizeText(scrollValue: Int, view: TextView) {
        if (originalTextSize == 0f) originalTextSize = binding.background.textSize
        val resizingBias = originalTextSize / backgroundViewHeight.toFloat()
        var textSize = abs(view.textSize + scrollValue * resizingBias)
        if (textSize < 0) textSize = 0f
        if (textSize > originalTextSize) textSize = originalTextSize
        view.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)
    }
}