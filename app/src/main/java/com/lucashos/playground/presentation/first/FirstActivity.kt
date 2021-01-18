package com.lucashos.playground.presentation.first

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.lucashos.playground.R

class FirstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        goToFragment(FirstFragment())
    }

    fun goToFragment(fragment: Fragment) =
        supportFragmentManager
            .beginTransaction()
            .add(R.id.content, fragment)
            .commit()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (resultCode) {
            Activity.RESULT_OK -> {
                Toast.makeText(this, "RESULT_OK", Toast.LENGTH_SHORT).show()
                supportFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                finish()
            }
            Activity.RESULT_CANCELED -> {
                Toast.makeText(this, "RESULT_CANCELED", Toast.LENGTH_SHORT).show()

            }
        }
    }
}