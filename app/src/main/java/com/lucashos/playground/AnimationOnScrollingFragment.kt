package com.lucashos.playground

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.lucashos.playground.databinding.FragmentAnimationOnScrollingBinding
import kotlinx.android.synthetic.main.fragment_animation_on_scrolling.*

class AnimationOnScrollingFragment : Fragment() {
    lateinit var binding: FragmentAnimationOnScrollingBinding

    companion object {
        fun newInstance(): AnimationOnScrollingFragment = AnimationOnScrollingFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_animation_on_scrolling, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addScrollingAnimation()
    }

    private fun addScrollingAnimation() {
        this.scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (isViewVisible(this.image)) moveView(scrollY - oldScrollY, this.image, this.scrollView)
        })
    }

    private fun moveView(scrollValue: Int, view: View, scrollView: NestedScrollView) {
        val viewPortSize = scrollView.measuredHeight.toFloat()
        val viewBias = getScrollViewFullSize() - viewPortSize
        val dimensionsBias = (scrollView.right.toFloat() + view.width) / viewBias
        var targetPosition = view.x + (scrollValue.toFloat() * dimensionsBias)
        if (targetPosition < 0) targetPosition = 0f
        if (targetPosition + view.width > scrollView.right) targetPosition = scrollView.right.toFloat() - view.width
        view.x = targetPosition
    }

    private fun isViewVisible(view: View): Boolean {
        val scrollBounds = Rect()
        this.scrollView.getHitRect(scrollBounds)
        return view.getLocalVisibleRect(scrollBounds)
    }

    private fun getScrollViewFullSize() = scrollView[0].height
}