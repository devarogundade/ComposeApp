package dev.arogundade.composeapp.models

import java.util.*

data class User(
    val id: Int,
    val username: String,
    val photoUrl: String,
    val emailAddress: String,
    val createdAt: Date
)