package com.kotlin.lamelapharmacy


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth



class MainActivity2 : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
// ...
// Initialize Firebase Auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_LamelaPharmacy)
        setContentView(R.layout.activity_main2)
    }

    fun Click(view: View) {
        val intent = Intent(this,Login::class.java)
        startActivity(intent)
        finish()
    }
}