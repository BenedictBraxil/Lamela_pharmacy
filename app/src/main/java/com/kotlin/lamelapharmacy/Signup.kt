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



class Signup : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var emailInput: EditText
    private lateinit var emailcontainer: TextInputLayout
    private lateinit var passwordInput: EditText
    private lateinit var passwordcontainer: TextInputLayout
    private lateinit var confirmpasswordInput: EditText
    private lateinit var confirmcontainer: TextInputLayout
    private lateinit var SignUpButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup )
        auth = Firebase.auth

        emailInput = findViewById(R.id.sign_upemail)
        emailcontainer = findViewById(R.id.emailcontainer)

        passwordInput = findViewById(R.id.sign_uppassword)
        passwordcontainer = findViewById(R.id.passwordcontainer)

        confirmpasswordInput = findViewById(R.id.sign_upconfirm)
        confirmcontainer = findViewById(R.id.confirmcontainer)
        SignUpButton = findViewById(R.id.signupbtn)

        emailInput.addTextChangedListener(validateSignUp)
        passwordInput.addTextChangedListener(validateSignUp)
        confirmpasswordInput.addTextChangedListener(validateSignUp)

        SignUpButton.setOnClickListener {
            SignUpButton.isEnabled = false
            emailInput.isEnabled = false
            passwordInput.isEnabled = false
            confirmpasswordInput.isEnabled = false

            val dialog = Dialog(this,)
            dialog.setContentView(R.layout.dialog_wait)
            if (dialog.window != null){
                dialog.window!!.setBackgroundDrawable(ColorDrawable(0))
            }
            dialog.show()

            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful){
                    startActivity(Intent(this,Login::class.java))
                    finish()
                    Toast.makeText(this,"Account created successful",Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this, it.exception.toString(),Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    private val validateSignUp = object : TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            val emi1 = emailInput.text.toString().trim().lowercase()
            val pass = passwordInput.text.toString().trim().lowercase()
            val con = confirmpasswordInput.text.toString().trim().lowercase()

            fun validateEmail(): Boolean{
                if (emi1.isBlank()){
                    emailcontainer.isErrorEnabled = true
                    emailcontainer.error = "This field is required"
                    SignUpButton.isEnabled = false
                    return false
                }else{
                    emailcontainer.isErrorEnabled = false
                    SignUpButton.isEnabled = true
                    return true
                }
            }
            fun validatePassword(): Boolean{
                if (pass.isBlank()){
                    passwordcontainer.isErrorEnabled = true
                    passwordcontainer.error = "This field is required"
                    SignUpButton.isEnabled = false
                    return false
                }else if (pass.length < 8){
                    passwordcontainer.isErrorEnabled = true
                    passwordcontainer.error = "password is too weak(8-16)"
                    SignUpButton.isEnabled = false
                    return false
                }else{
                    passwordcontainer.isErrorEnabled = false
                    SignUpButton.isEnabled = true
                    return true
                }
            }
            fun validateConfirmpassword(): Boolean{
                if (con.isBlank()){
                    confirmcontainer.isErrorEnabled = true
                    confirmcontainer.error = "This field is required"
                    SignUpButton.isEnabled = false
                    return false
                }else if (con != pass){
                    passwordcontainer.isErrorEnabled = true
                    passwordcontainer.error = "passwords don't match"
                    confirmcontainer.isErrorEnabled = true
                    confirmcontainer.error = "passwords don't match"
                    SignUpButton.isEnabled = false
                    return false
                }else{
                    confirmcontainer.isErrorEnabled = false
                    SignUpButton.isEnabled = true
                    return true
                }
            }
            SignUpButton.isEnabled = validateEmail() && validatePassword() && validateConfirmpassword()
        }

        override fun afterTextChanged(p0: Editable?) {

        }

    }

    fun Login(view: View) {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()
    }


}


