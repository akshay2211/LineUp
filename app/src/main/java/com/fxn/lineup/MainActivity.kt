package com.fxn.lineup

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.fxn.fragments.HomeFragment
import com.fxn.utilities.Utility

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utility.window(this)
        setContentView(R.layout.activity_main)
        Utility.getScreensize(this)
        supportFragmentManager.beginTransaction().replace(R.id.container, HomeFragment.newInstance("", "")).commitNow()
    }
}
