package com.kotlin.lamelapharmacy

import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase



class Login : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    private lateinit var emailInput: EditText
    private lateinit var emailcontainer: TextInputLayout
    private lateinit var passwordInput: EditText
    private lateinit var passwordcontainer: TextInputLayout
    private lateinit var LoginButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = Firebase.auth

        emailInput = findViewById(R.id.loginemail)
        emailcontainer = findViewById(R.id.loginemailcontainer)

        passwordInput = findViewById(R.id.loginpassword)
        passwordcontainer = findViewById(R.id.loginpasswordcontainer)
        LoginButton = findViewById(R.id.loginbtn)

        emailInput.addTextChangedListener(validateLogin)
        passwordInput.addTextChangedListener(validateLogin)

        LoginButton.setOnClickListener {
            LoginButton.isEnabled = false
            emailInput.isEnabled = false
            passwordInput.isEnabled = false

            val dialog = Dialog(this)
            dialog.setContentView(R.layout.dialog_loading)
            if (dialog.window != null){
                dialog!!.window!!.setBackgroundDrawable(ColorDrawable(0))
            }
            dialog.show()

            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful){
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }else{
                    Toast.makeText(this, it.exception.toString(),Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    private val validateLogin = object : TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            val emi = emailInput.text.toString().trim().lowercase()
            val pass = passwordInput.text.toString().trim().lowercase()

            fun validateEmail(): Boolean{
                if (emi.isBlank()){
                    emailcontainer.isErrorEnabled = true
                    emailcontainer.error = "This field is required"
                    LoginButton.isEnabled = false
                    return false
                }else{
                    emailcontainer.isErrorEnabled = false
                    LoginButton.isEnabled = true
                    return true
                }
            }
            fun validatePassword(): Boolean{
                if (pass.isBlank()){
                    passwordcontainer.isErrorEnabled = true
                    passwordcontainer.error = "incorrect password"
                    LoginButton.isEnabled = false
                    return false
                }else{
                    passwordcontainer.isErrorEnabled = false
                    LoginButton.isEnabled = true
                    return true
                }
            }
            LoginButton.isEnabled = validateEmail() && validatePassword()
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun afterTextChanged(p0: Editable?) {

        }

    }

    fun Register(view: View) {
        val intent = Intent(this,Signup::class.java)
        startActivity(intent)
        finish()
    }



    override fun onStart() {
        super.onStart()
        auth = Firebase.auth
        val currentUser = auth.currentUser
        if (currentUser != null){
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }

}