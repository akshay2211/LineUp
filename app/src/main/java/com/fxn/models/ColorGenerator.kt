package com.fxn.models

import android.content.Context
import android.support.v4.content.ContextCompat
import com.fxn.lineup.R
import java.io.Serializable

/**
 * Created by akshay on 25/06/18.
 */

class ColorGenerator(var type: Int) : Serializable {

    companion object {
        var GREY: Int = 0
        var RED: Int = 1
        var BLUE: Int = 2
        var GREEEN: Int = 3
        var YELLOW: Int = 4
    }

    fun getColor(context: Context): Int {
        when (type) {
            RED -> return ContextCompat.getColor(context, R.color.red_color)
            BLUE -> return ContextCompat.getColor(context, R.color.blue_color)
            GREEEN -> return ContextCompat.getColor(context, R.color.green_color)
            YELLOW -> return ContextCompat.getColor(context, R.color.yellow_color)
            else -> return ContextCompat.getColor(context, R.color.grey_color)
        }
    }

    fun getDrawable(): Int? {
        when (type) {
            RED -> return R.drawable.red_bg
            BLUE -> return R.drawable.blue_bg
            GREEEN -> return R.drawable.green_bg
            YELLOW -> return R.drawable.yellow_bg
            else -> return R.drawable.grey_bg
        }
    }


}