package com.di

import android.content.Context
import com.data.Repository
import com.retrofit.ApiConfig

object Injection {
    fun repositoryProvide(context: Context): Repository {
        val apiService = ApiConfig.getApiService(context) // Memastikan context diteruskan di sini
        return Repository(apiService, context) // Memastikan context diteruskan ke Repository
    }
}
