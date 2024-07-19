package com.example.bottomnavyt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class Login : AppCompatActivity() {
    var loginbtn: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginbtn = findViewById<Button>(R.id.btnLogin)
        loginbtn?.setOnClickListener(View.OnClickListener {
            val i = Intent(this@Login, MainActivity::class.java)
            startActivity(i)
            finish()
        })
    }
}