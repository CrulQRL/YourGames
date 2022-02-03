package com.faqrulans.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DeveloperResponse(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("games_count")
    val gamesCount: Int,

    @field:SerializedName("image_background")
    val imageBackground: String,
)
