package com.ui.login

import androidx.lifecycle.ViewModel
import com.data.Repository

class LoginViewModel(private val storyRepo: Repository): ViewModel() {
    fun postLogin(email: String, pass: String) = storyRepo.login(email, pass)
}