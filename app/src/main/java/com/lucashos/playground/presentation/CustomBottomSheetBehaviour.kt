package com.lucashos.playground.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.ScrollView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.lucashos.playground.R


class CustomBottomSheetBehaviour<V : View>(context: Context, attrs: AttributeSet) :
    BottomSheetBehavior<V>(context, attrs) {

    override fun onInterceptTouchEvent(
        parent: CoordinatorLayout,
        child: V,
        event: MotionEvent
    ): Boolean {
        val scroll = child.findViewById<ScrollView>(R.id.scroll)

        if (isExpanded() && !isScrollOnTop(scroll)) {
            return false
        }
        return super.onInterceptTouchEvent(parent, child, event)
    }

    private fun isScrollOnTop(scroll: ScrollView): Boolean {
        return scroll.scrollY == 0
    }

    private fun isExpanded(): Boolean =
        this.state == STATE_EXPANDED



}