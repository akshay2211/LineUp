package com.fxn.lineup

import android.app.Application
import com.activeandroid.ActiveAndroid

/**
 * Created by akshay on 27/05/18.
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        //ActiveAndroid.initialize(Configuration.Builder(this).setDatabaseName("line.db").create());
        ActiveAndroid.initialize(this)
    }

}