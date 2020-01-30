package com.lucashos.playground.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.lucashos.playground.R
import com.lucashos.playground.core.BackendException
import com.lucashos.playground.data.repotistory.MockyRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_overlap,
                R.id.nav_parallax,
                R.id.nav_scrolling
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        getSuccess()
        getError()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    @SuppressLint("CheckResult")
    fun getSuccess() {
        Log.d("X", "Sending success")
        MockyRepository.getSuccess()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("X", it.statusCode.toString())
                Log.d("X", it.data.printed_name)
            }, {
                if (it is BackendException) {
                    Log.d("X", it.errorResponse.title)
                } else
                    Log.d("X", it.toString())
            })
    }

    @SuppressLint("CheckResult")
    fun getError() {
        Log.d("X", "Sending error")
        MockyRepository.getError()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("X", it.statusCode.toString())
                Log.d("X", it.data.printed_name)
            }, {
                if (it is BackendException) {
                    Log.d("X", it.errorResponse.title)
                } else
                    Log.d("X", it.toString())
            })
    }
}
