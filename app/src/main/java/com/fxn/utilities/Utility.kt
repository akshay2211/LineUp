package com.fxn.utilities

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.PorterDuff
import android.os.Build
import android.support.annotation.ColorInt
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.text.Spannable
import android.text.format.DateUtils
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import com.fxn.lineup.R
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by akshay on 11/05/18.
 */
class Utility {
    companion object {

        val dateTime = SimpleDateFormat("dd-MM-yyy hh:mm:ss:aa")
        fun window(context: Activity) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                //context.window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                //      WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
                context.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            }
        }

        fun getDateTimeString(): String {
            return dateTime.format(Date())
        }

        fun getScreensize(context: Activity): DisplayMetrics {
            var disp: DisplayMetrics = context.resources.displayMetrics
            Constant.widthPx = disp.widthPixels
            Constant.heightPx = disp.heightPixels
            Constant.widthDp = (disp.widthPixels / disp.density).toInt()
            Constant.heightDp = (disp.heightPixels / disp.density).toInt()
            return disp
        }

        fun getCountedTime(date: String): String {
            val time = dateTime.parse(date).time
            return DateUtils.getRelativeTimeSpanString(time, System.currentTimeMillis(),
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

        private fun datepickerwork(view: View, context: Activity) {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val hour = c.get(Calendar.HOUR_OF_DAY)
            val minutes = c.get(Calendar.MINUTE)

            val dpd = DatePickerDialog(context, DatePickerDialog.OnDateSetListener { _, yeardate, monthOfYear, dayOfMonth ->
                TimePickerDialog(context, TimePickerDialog.OnTimeSetListener { _, hourtime, mintime ->
                    val c1 = Calendar.getInstance()
                    c1.set(Calendar.YEAR, yeardate)
                    c1.set(Calendar.MONTH, monthOfYear)
                    c1.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    c1.set(Calendar.HOUR_OF_DAY, hourtime)
                    c1.set(Calendar.MINUTE, mintime)
                    var s: String = SimpleDateFormat("MMM dd HH:mm:aa").format(c1.time).toString()
                    var str: Spannable = Spannable.Factory.getInstance().newSpannable(s)
                    str.setSpan(CustomTypefaceSpan(ResourcesCompat.getFont(context.applicationContext, R.font.quicksand_bold)),
                            0, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    //  view.alarm.setText(str, TextView.BufferType.SPANNABLE)
                }, hour, minutes, true).show()
            }, year, month, day)
            // view.alarm.setOnClickListener { dpd.show() }

        }
    }
}