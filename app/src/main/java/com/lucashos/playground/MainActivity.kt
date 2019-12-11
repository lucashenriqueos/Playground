package com.lucashos.playground

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import kotlinx.android.synthetic.main.activity_main.*
import android.R.attr.bottom
import android.R.attr.top
import android.graphics.Rect
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (isViewVisible(this.image)) moveImage(scrollY - oldScrollY)
        })
    }

    private fun moveImage(scrollValue: Int) {
        val dimensionsBias = this.scrollView.bottom.toFloat() / this.scrollView.right.toFloat()
        var targetPosition = this.image.x + (scrollValue.toFloat() * dimensionsBias)
        if (targetPosition < 0) targetPosition = 0f
        if (targetPosition + this.image.width > this.scrollView.right) targetPosition = this.scrollView.right.toFloat() - this.image.width
        this.image.x = targetPosition
    }

    private fun isViewVisible(view: View): Boolean {
        val scrollBounds = Rect()
        this.scrollView.getDrawingRect(scrollBounds)
        val top = view.y
        val bottom = top + view.height
        return scrollBounds.top < top && scrollBounds.bottom > bottom
    }
}
