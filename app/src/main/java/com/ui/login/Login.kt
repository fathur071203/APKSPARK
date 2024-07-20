package com.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.ViewModelFactory
import com.data.UserPreferences
import com.data.Username
import com.example.bottomnavyt.MainActivity
import com.example.bottomnavyt.databinding.ActivityLoginBinding
import com.data.Result

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var modelUser: Username = Username()
    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val viewModelFactory: ViewModelFactory = ViewModelFactory.getInstance(this)
        val loginViewModel: LoginViewModel by viewModels {
            viewModelFactory
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.EmailText.text.toString()
            val password = binding.PasswordText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email dan Password harus diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            loginViewModel.postLogin(email, password).observe(this) {
                if (it != null) {
                    when (it) {
                        is Result.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is Result.Success -> {
                            binding.progressBar.visibility = View.GONE
                            val intent = Intent(this@Login, MainActivity::class.java)
                            startActivity(intent)
                        }
                        is Result.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Log.e("Login", "Error: ${it.error}")
                            Toast.makeText(this, "Login gagal: ${it.error}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun tokenSave(token: String) {
        modelUser.tokenName = token
        userPreferences.setUserPreferences(modelUser)
    }
}


