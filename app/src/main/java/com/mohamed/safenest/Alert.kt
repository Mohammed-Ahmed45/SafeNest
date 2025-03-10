package com.mohamed.safenest

data class Alert(
    val id: Int,
    val sensorID: String,
    val type: String,
    val status: String,
    val createdAt: String,
    val guidance: String
)
