package com.kotlin.lamelapharmacy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class Contact_Us: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_contact__us, container, false)

        val contactAddressTextView: TextView = view.findViewById(R.id.address)
        val contactPhoneTextView: TextView = view.findViewById(R.id.phone)
        val contactEmailTextView: TextView = view.findViewById(R.id.email)

        contactAddressTextView.text = getString(R.string.contact_address)
        contactPhoneTextView.text = getString(R.string.contact_phone)
        contactEmailTextView.text = getString(R.string.contact_email)

        return view
    }
}