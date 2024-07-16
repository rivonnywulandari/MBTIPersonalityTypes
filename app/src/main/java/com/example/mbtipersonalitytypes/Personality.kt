package com.example.mbtipersonalitytypes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Personality(
    val name: String,
    val type: String,
    val item: String,
    val frequencyMales: String,
    val frequencyFemales: String,
    val description: String,
    val strengths: String,
    val weaknesses: String,
    val famousPerson: String,
    val photo: Int
) : Parcelable
