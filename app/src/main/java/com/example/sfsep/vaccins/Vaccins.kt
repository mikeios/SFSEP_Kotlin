package com.example.sfsep.vaccins

import android.content.res.Resources
import com.example.sfsep.R

// Classe Vaccins
class Vaccin(
    var nom:String,
    var commentaire:String?,
    var vivant: Boolean = false,
    var recommandé:Boolean = true,
    var contreIndiqué:Boolean = false) {

}

fun getResourceString(id: Int) : String {
    return Resources.getSystem().getString(id)
}

// Les vaccins de base

var ROR = Vaccin(
    getResourceString(R.string.ROR),
    getResourceString(R.string.commentROR),
    true,
    false
)
var VHB = Vaccin(
    getResourceString(R.string.VHB),
    getResourceString(R.string.commentVHB),
    false,
    false)
var VZV = Vaccin(
    "VZV",
    getResourceString(R.string.commentVZV),
    true,
    !VaccinAssistantExpert.seroVZV)
var VHA = Vaccin(
    getResourceString(R.string.VHA),
    getResourceString(R.string.commentVHA),
    false,
    false)
var DTP = Vaccin(
    "DTP",
    getResourceString(R.string.commentDTP),
    false,
    false)

// Les vaccins particuliers
var grippe = Vaccin(
    getResourceString(R.string.grippe),
    getResourceString(R.string.commentGrippe),
    false,
    false)
var pneumocoque = Vaccin(
    getResourceString(R.string.pneumocoque),
    getResourceString(R.string.commentPneumocoque),
    false,
    false)
var autresVVA = Vaccin(
    getResourceString(R.string.autresVVA),
    getResourceString(R.string.commentVVA),
    true,
    false)

// Les matrices
var vaccinArray = listOf<Vaccin>(ROR, VHB, VZV, VHA, DTP, grippe, pneumocoque, autresVVA)
var vaccinsRecommandés = listOf<Vaccin>()
var vaccinsInterdits = listOf<Vaccin>()