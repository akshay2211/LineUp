package com.fxn.utilities

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.view.View
import android.view.ViewAnimationUtils

/**
 * Created by akshay on 11/05/18.
 */
class Animation {
    fun registerCircularRevealAnimation(context: Context?, view: View, revealSettings: RevealAnimationSetting, startColor: Int, endColor: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                override fun onLayoutChange(v: View, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) {
                    v.removeOnLayoutChangeListener(this)
                    val cx = revealSettings.centerX
                    val cy = revealSettings.centerY
                    val width = revealSettings.width
                    val height = revealSettings.height
                    val duration = context!!.applicationContext.resources.getInteger(android.R.integer.config_mediumAnimTime)

                    //Simply use the diagonal of the view
                    val finalRadius = Math.sqrt((width * width + height * height).toDouble()).toFloat()
                    val anim = ViewAnimationUtils.createCircularReveal(v, cx, cy, 0f, finalRadius).setDuration(duration.toLong())
                    anim.interpolator = FastOutSlowInInterpolator()
                    anim.start()
                    startColorAnimation(view, startColor, endColor, duration)
                }
            })
        }
    }

    internal fun startColorAnimation(view: View, startColor: Int, endColor: Int, duration: Int) {
        val anim = ValueAnimator()
        anim.setIntValues(startColor, endColor)
        anim.setEvaluator(ArgbEvaluator())
        anim.addUpdateListener { valueAnimator -> view.setBackgroundColor(valueAnimator.animatedValue as Int) }
        anim.duration = duration.toLong()
        anim.start()
    }

    class RevealAnimationSetting(centerX: Int, centerY: Int, width: Int, height: Int) {
        val centerX: Int = centerX
        val centerY: Int = centerY
        val width: Int = width
        val height: Int = height
    }
}

