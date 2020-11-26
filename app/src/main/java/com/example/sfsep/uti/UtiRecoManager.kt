package com.example.diaporamaphotos

import android.content.res.Resources
import com.example.sfsep.R

object UtiRecoManager {

    enum class AgeTier {
        less65, between65And75, over75;

        var value = 0
        get() = when(this) {
            less65 -> 0
            between65And75 -> 1
            over75 -> 2
        }
    }

    enum class dmtRaisesRisk {
        yes, no, possibly;
    }

    val treatments = Resources.getSystem().getStringArray(R.array.dmt)
    var ageTier = AgeTier.less65
    var isMale = true
    var frailty = false
    var tractusAbnormality = false
    var reflux = false
    var highPression = false
    var corticosteroids = false
    var currentTreatment = treatments.first()
    var hypogamma = false
    var neutropenia = false
    var badRenalFunction = false
    var recurrentUTI = false
    var bolus = false
    var plasmapheresis = false
    var fampridine = false
    val immunosuppressiveTreatment:Boolean
        get() = (currentTreatment != "IFN-β")
            && (currentTreatment != "Glatiramer Acetate")
            && (currentTreatment != treatments.first())

    var reco_recurrentUtiText:String = ""
        get() = if (recurrentUTI) {
            Resources.getSystem().getString(R.string.oui)
        } else {
            Resources.getSystem().getString(R.string.non)
        }

    var reco_screenColonizationText:String = ""
        get() = if (colonizationShouldBeScreened()) {
            Resources.getSystem().getString(R.string.uti_screening)
        } else {
            Resources.getSystem().getString(R.string.non_screening)
        }

    var reco_screenBeforeBudText:String = ""
        get() = if (colonizationShouldBeScreenedBeforeBUD()) {
            Resources.getSystem().getString(R.string.uti_screening_bud)
        } else {
            Resources.getSystem().getString(R.string.non)
        }

    var reco_preventiveTreatmentText:String = ""
        get() = if (recurrentUTI) {
            Resources.getSystem().getString(R.string.uti_prevention)
        } else {
            Resources.getSystem().getString(R.string.non)
        }



    fun atRiskSituation() : Boolean {

        return (this.ageTier == AgeTier.over75
                || (this.ageTier == AgeTier.between65And75 && frailty)
                || isMale
                || tractusAbnormality
                || immunosuppressiveTreatment
                || hypogamma
                || neutropenia
                || badRenalFunction
                )
    }

    fun treatmentRaisesRisk() : dmtRaisesRisk {
        if (currentTreatment == "Mitoxantrone"
                || currentTreatment == "Alemtuzumab"
                || currentTreatment == "Cyclophosphamide"
                || currentTreatment == "Rituximab") {
            return dmtRaisesRisk.yes
        } else if (currentTreatment == "Ocrelizumab" && hypogamma) {
            return dmtRaisesRisk.possibly
        } else {
            return dmtRaisesRisk.no
        }
    }

    fun colonizationShouldBeScreened() : Boolean {
        return (immunosuppressiveTreatment && hypogamma)
    }

    fun colonizationShouldBeScreenedBeforeBUD() : Boolean {
        return highPression
                || recurrentUTI
                || reflux
    }

    fun additionnalReco() : String? {
        var additionalString = ""

        if (bolus) {
            additionalString = additionalString + Resources.getSystem().getString(R.string.uti_bolusReco) + "\r\r"
        }

        if (plasmapheresis) {
            additionalString = additionalString + Resources.getSystem().getString(R.string.uti_plasmaReco) + "\r\r"
        }
        if (fampridine) {
            additionalString = additionalString + Resources.getSystem().getString(R.string.uti_fampridineReco) + "\r\r"
        }

        if (additionalString == "") {
            return  null
        } else {
            return additionalString
        }
    }

}