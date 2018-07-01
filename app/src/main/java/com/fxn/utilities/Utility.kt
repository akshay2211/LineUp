package com.fxn.utilities

import android.app.Activity
import android.graphics.PorterDuff
import android.os.Build
import android.support.annotation.ColorInt
import android.support.v4.content.ContextCompat
import android.text.format.DateUtils
import android.util.DisplayMetrics
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import java.util.*


/**
 * Created by akshay on 11/05/18.
 */
class Utility {
    companion object {
        fun window(context: Activity) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                //context.window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                //      WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
                context.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            }
        }

        fun getScreensize(context: Activity): DisplayMetrics {
            var disp: DisplayMetrics = context.resources.displayMetrics
            Constant.widthPx = disp.widthPixels
            Constant.heightPx = disp.heightPixels
            Constant.widthDp = (disp.widthPixels / disp.density).toInt()
            Constant.heightDp = (disp.heightPixels / disp.density).toInt()
            return disp
        }

        fun getCountedTime(date: Date): String {
            return DateUtils.getRelativeTimeSpanString(date.time, System.currentTimeMillis(),
                    DateUtils.SECOND_IN_MILLIS, DateUtils.FORMAT_ABBREV_ALL).toString()
        }

        fun setCursorColor(view: EditText, @ColorInt color: Int) {
            try {
                // Get the cursor resource id
                var field = TextView::class.java.getDeclaredField("mCursorDrawableRes")
                field.isAccessible = true
                val drawableResId = field.getInt(view)

                // Get the editor
                field = TextView::class.java.getDeclaredField("mEditor")
                field.isAccessible = true
                val editor = field.get(view)

                // Get the drawable and set a color filter
                val drawable = ContextCompat.getDrawable(view.context, drawableResId)
                drawable!!.setColorFilter(color, PorterDuff.Mode.SRC_IN)
                val drawables = arrayOf(drawable, drawable)

                // Set the drawables
                field = editor.javaClass.getDeclaredField("mCursorDrawable")
                field.isAccessible = true
                field.set(editor, drawables)
            } catch (ignored: Exception) {
            }

        }
    }


    /*public static void getScreenSize(Activity context) {
       displayMetrics = context.getResources().getDisplayMetrics();
       Constants.HeightPX = displayMetrics.heightPixels;
       Constants.WidthPX = displayMetrics.widthPixels;
       Constants.HeightDP = (int) (Constants.HeightPX / displayMetrics.density);
       Constants.WidthDP = (int) (Constants.WidthPX / displayMetrics.density);
       Log.e("WidthPX HeightPX", "" + Constants.WidthPX + " " + Constants.HeightPX);
       Log.e("WidthDP HeightDP", "" + Constants.WidthDP + " " + Constants.HeightDP);
   }*/
}