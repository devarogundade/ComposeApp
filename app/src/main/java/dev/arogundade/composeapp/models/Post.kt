package dev.arogundade.composeapp.models

import java.util.*

data class Post(
    val id: Int,
    val title: String,
    val description: String,
    val body: String,
    val photoUrl: String,
    val author: String,
    val createdAt: Date
)
