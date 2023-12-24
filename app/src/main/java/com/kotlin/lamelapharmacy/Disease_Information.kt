package com.kotlin.lamelapharmacy

import android.os.Bundle
import android.text.method.TextKeyListener.clear
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast

class Disease_Information : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_disease__information, container, false)

        val firstNameEditText:EditText = view.findViewById(R.id.txtFname)
        val secondNameEditText:EditText = view.findViewById(R.id.txtSname)
        val dobEditText:EditText = view.findViewById(R.id.txtDob)
        val ageEditText:EditText = view.findViewById(R.id.txtage)
        val feelingEditText:EditText = view.findViewById(R.id.txtFeel)
        val historyEditText:EditText = view.findViewById(R.id.txthistory)
        val genderSpinner:Spinner = view.findViewById(R.id.gender)
        val submitButton:Button = view.findViewById(R.id.submitButton)

        submitButton.setOnClickListener {
            // Retrieve user input
            val firstName = firstNameEditText.text.toString()
            val secondName = secondNameEditText.text.toString()
            val gender = genderSpinner.selectedItem.toString()
            val dob = dobEditText.text.toString()
            val age = ageEditText.text.toString()
            val feeling = feelingEditText.text.toString()
            val history = historyEditText.text.toString()

            // Perform any further processing or validation as needed

            // Display a message
            val sent =
                "Sent successfully\nFirst Name: $firstName\nSecond Name: $secondName\nGender: $gender\nDob :$dob\nAge :$age\nFeeling :$feeling\nHistory :$history"
            Toast.makeText(requireContext(), sent, Toast.LENGTH_SHORT).show()

        }

        return view
    }
}

