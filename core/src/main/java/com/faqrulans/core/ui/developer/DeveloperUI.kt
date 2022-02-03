package com.faqrulans.core.ui.developer

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DeveloperUI(
    val id: String,
    val name: String,
    val gamesCount: Int,
    val imageBackground: String,
    val backgroundColor: Int
): Parcelable
