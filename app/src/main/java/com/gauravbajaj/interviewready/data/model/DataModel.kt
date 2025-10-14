package com.gauravbajaj.interviewready.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataModel(
    val id: String,
    val name: String,
    val email: String,
    val avatarUrl: String? = null
) : Parcelable