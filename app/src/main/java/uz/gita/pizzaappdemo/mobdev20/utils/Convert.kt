package uz.gita.pizzaappdemo.mobdev20.utils

import android.content.res.Resources.getSystem

val Int.dp: Int get() = (this / getSystem().displayMetrics.density).toInt()
val Int.px: Int get() = (this * getSystem().displayMetrics.density).toInt()

