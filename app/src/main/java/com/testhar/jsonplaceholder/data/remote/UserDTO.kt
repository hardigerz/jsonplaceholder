package com.testhar.jsonplaceholder.data.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDTO(
    val id : Int,
    val name : String,
    val email : String
)
