package com.example.sfsep.generalModel

import android.app.Application
import android.content.Context

object ResourcesManager {
    var context:Context? = null
    fun getResourceString(id: Int) : String {
        return context?.getString(id) ?: ""
    }
}

