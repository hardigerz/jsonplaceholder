package com.testhar.jsonplaceholder.data.remote

import retrofit2.http.GET

interface ApiService {
    interface ApiService {
        @GET("users")
        suspend fun getUsers(): List<UserDTO>
    }
}