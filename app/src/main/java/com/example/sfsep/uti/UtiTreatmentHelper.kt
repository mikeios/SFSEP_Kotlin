package com.example.diaporamaphotos

import android.content.res.Resources
import com.example.sfsep.R

/*interface UtiTreatmentHelperDelegate {
    fun refreshUI()
}*/



object UtiTreatmentHelper {
    val resourcesManager = Resources.getSystem()


    enum class InfectionType {
        cystite, pna, colonisation, masculine;

        val value: Int
            get() = when (this) {
                cystite -> 0
                pna -> 1
                colonisation -> 2
                masculine -> 3
            }
    }

    enum class Genre {
        male, female;

        val value: Int
            get() = when (this) {
                male -> 0
                female -> 1
            }
    }

    // var delegate: UtiTreatmentHelperDelegate? = null

    var genre = Genre.female
    var infection = InfectionType.cystite
    var pauciSymptomatic = false
    var treatmentCanBeDelayed = false
    var signesDeGravite = false
    var chocSeptique = false
    var pregnancy = false

    fun getProbabilistTreatment(): Array<String>? {
        when (infection) {
            InfectionType.colonisation -> return null
            InfectionType.cystite ->
                if (pregnancy) {
                    return arrayOf("Fosfomycine - trometamol", "Pivmecillinam")
                } else {
                    if (!pauciSymptomatic && !treatmentCanBeDelayed) {
                        return arrayOf("Nitrofurantoïne", "Fosfomycine - trometamol")
                    } else {
                        return null
                    }
                }
            InfectionType.pna ->
                if (chocSeptique) {
                    return arrayOf(
                        resourcesManager.getString(R.string.uti_pna_choc1),
                        resourcesManager.getString(R.string.uti_pna_choc2),
                        resourcesManager.getString(R.string.uti_pna_choc3)
                    )
                } else if (signesDeGravite) {
                    return arrayOf(
                        resourcesManager.getString(R.string.uti_pna_choc1),
                        resourcesManager.getString(R.string.uti_pna_choc2),
                        resourcesManager.getString(R.string.uti_pna_grave1)
                    )
                } else {
                    return arrayOf(
                        resourcesManager.getString(R.string.uti_pna_simple1),
                        resourcesManager.getString(R.string.uti_pna_simple2),
                        resourcesManager.getString(R.string.uti_pna_contreIndic)
                    )

                }
            InfectionType.masculine ->
                if (chocSeptique) {
                    return arrayOf(
                        resourcesManager.getString(R.string.uti_pna_choc1),
                        resourcesManager.getString(R.string.uti_pna_choc2),
                        resourcesManager.getString(R.string.uti_pna_choc3)
                    )
                } else if (signesDeGravite) {
                    return arrayOf(
                        resourcesManager.getString(R.string.uti_pna_choc1),
                        resourcesManager.getString(R.string.uti_pna_choc2),
                        resourcesManager.getString(R.string.uti_pna_grave1)
                    )
                } else if (!pauciSymptomatic && !treatmentCanBeDelayed) {
                    return arrayOf(
                        resourcesManager.getString(R.string.uti_pna_simple2) + " " + resourcesManager.getString(
                            R.string.uti_saufSiFQRecent
                        ),
                        resourcesManager.getString(R.string.uti_prostatite3)
                    )

                } else {
                    return null
                }


        }
    }

    fun getRelayTreatment() : Array<String> {
        when (infection) {
            InfectionType.colonisation ->
                return  arrayOf(
                    "1 - Amoxicilline",
                    "2 - Pivmecillinam",
                    "3 - Fosfomycine-trometamol",
                    "4 - Trimethoprime (TMP)",
                    "5 - Nitrofurantoïne, cotrimoxazole, " + resourcesManager.getString(R.string.uti_amoxClav) + ", Cefixime",
                    resourcesManager.getString(R.string.uti_warningPregnancy)
                )
            InfectionType.cystite ->
                if (pregnancy) {
                    return arrayOf(
                        "1 - Amoxicilline",
                        "2 - Trimethoprime (TMP)",
                        "3 - Nitrofurantoïne, Cotrimoxazole, " + resourcesManager.getString(R.string.uti_amoxClav) + ", Cefixime",
                        resourcesManager.getString(R.string.uti_warningPregnancy)
                    )
                } else {
                    return arrayOf(
                        "1 - Amoxicilline",
                        "2 - Pivmecillinam",
                        "3 - Nitrofurantoïne",
                        "4 - Fosfomycine-trometamol",
                        "5 - Triméthoprime (TMP)"
                    )
                }
            InfectionType.pna ->
                return  arrayOf(
                    "1 - Amoxicilline",
                    "2 - " + resourcesManager.getString(R.string.uti_amoxClav),
                    "3 - " + resourcesManager.getString(R.string.uti_pna_simple2),
                    "4 - Cefixime",
                    "5 - Cotrimoxazole (SMX-TMP)"
                )
            InfectionType.masculine ->
                return arrayOf(
                    "1 - " + resourcesManager.getString(R.string.uti_pna_simple2),
                    "2 - Cotrimoxazole (SMX-TMP)",
                    "3 - Cefotaxime, ceftriaxone",
                    "4 - Céfoxitine (E. coli), piperacilline-tazobactam, temocilline",
                    "5 - " + resourcesManager.getString(R.string.uti_prostatite5)
                )
        }
    }

    fun getTreatmentDuration() : Array<String> {
        when (infection) {
            InfectionType.colonisation ->
                return  arrayOf(
                    "Fosfomycine - trometamol: " + 1.daysString(),
                    resourcesManager.getString(R.string.uti_other) + ": " + 7.daysString()
                )
            InfectionType.cystite ->
                if (pregnancy) {
                    return  arrayOf(
                        "Fosfomycine - trometamol: " + 1.daysString(),
                        resourcesManager.getString(R.string.uti_other) + ": " + 7.daysString()
                    )
                } else {
                    return arrayOf(
                        "Amoxicilline, pivmecilinam, nutrifurantoine: " + 7.daysString(),
                        "Fosfomycine- trometamol:" + resourcesManager.getString(R.string.uti_FosfoTroRegimen),
                        "TMP: " + 5.daysString()
                    )
                }
            InfectionType.pna ->
                return arrayOf(
                    10.daysString() + " " + resourcesManager.getString(R.string.uti_pna_rapidement_evolutive),
                    resourcesManager.getString(R.string.uti_other) + ": " + 14.daysString(),
                    resourcesManager.getString(R.string.ttt_prolonge)
                )

            InfectionType.masculine ->
                return arrayOf(
                    "Ciprofloxacine, levofloxacine, cotrimoxazole, " + resourcesManager.getString(R.string.uti_parenterale) + ": " + 14.daysString(),
                    resourcesManager.getString(R.string.uti_other) + ", " + resourcesManager.getString(R.string.uti_uropathie) + ": " + 21.daysString()
                )
        }
    }



}