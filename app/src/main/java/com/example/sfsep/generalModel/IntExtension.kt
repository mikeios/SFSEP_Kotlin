package com.example.diaporamaphotos

import android.content.res.Resources
import com.example.sfsep.R

fun Int.daysString():String {
    when (this) {
        1 -> return "1 " + Resources.getSystem().getString(R.string.jour)
        else -> return "$this " + Resources.getSystem().getString(R.string.jours)
    }
}