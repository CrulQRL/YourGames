package com.faqrulans.core.domain.model

data class Game(
    val id: String,
    val name: String,
    val released: String?,
    val rating: Double,
    val backgroundImage: String?
)
