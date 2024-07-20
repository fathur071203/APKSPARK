package com.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.response.LoginResponse
import com.response.SlotParkirResponse
import com.retrofit.ApiService

class Repository(private val apiService: ApiService, private val context: Context) {

    private val userPreferences = UserPreferences(context)

    fun login(email: String, pw: String): LiveData<Result<LoginResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.login(email, pw)
            userPreferences.setUserPreferences(Username(tokenName = response.accessToken))
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.e("Repository", "Login error: ${e.message}", e)
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getSlotParkir(): LiveData<Result<SlotParkirResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.SlotAll()
            Log.d("Repository", "Response received: $response")
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.e("Repository", "Error loading slot parkir: ${e.message}", e)
            emit(Result.Error("Failed to load data"))
        }
    }
}
