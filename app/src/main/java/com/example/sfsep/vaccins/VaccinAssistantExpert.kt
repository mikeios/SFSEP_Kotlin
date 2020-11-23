package com.example.sfsep.vaccins

import kotlin.properties.Delegates

object VaccinAssistantExpert {
    var seroVZV = false
    var vzvFait : Boolean by Delegates.observable(false) {
        prep, old, new ->
        if (new) {
            seroVZV = new
        }
    }
}