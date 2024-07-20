package com.retrofit

import com.response.LoginResponse
import com.response.SlotParkirResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("api/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @GET("api/slot-parkir")
    suspend fun SlotAll(): SlotParkirResponse
}
