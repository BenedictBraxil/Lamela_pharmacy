package com.kotlin.lamelapharmacy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Make_an_order: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_make_an_order, container, false)

        val medicineNameEditText: EditText = view.findViewById(R.id.medicineNameEditText)
        val quantityEditText: EditText = view.findViewById(R.id.quantityEditText)
        val prescriptionEditText: EditText = view.findViewById(R.id.prescriptionEditText)
        val submitOrderButton: Button = view.findViewById(R.id.submitOrderButton)

        submitOrderButton.setOnClickListener {
            // Retrieve user input
            val medicineName = medicineNameEditText.text.toString()
            val quantity = quantityEditText.text.toString()
            val prescription = prescriptionEditText.text.toString()

            // Validate and submit the order
            if (medicineName.isNotEmpty() && quantity.isNotEmpty()) {
                // TODO: Implement order submission logic here
                // You can send the order details to a server or perform other actions
                // For now, display a simple confirmation message
                val message = "Order received!\nMedicine: $medicineName\nQuantity: $quantity\nPrescription: $prescription"
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Please fill in all required fields", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
