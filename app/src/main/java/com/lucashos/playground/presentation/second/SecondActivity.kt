package com.lucashos.playground.presentation.second

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.lucashos.playground.R

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        goToFragment(FirstFragment())
    }


    fun goToFragment(fragment: Fragment) =
        supportFragmentManager
            .beginTransaction()
            .add(R.id.content, fragment)
            .commit()
}