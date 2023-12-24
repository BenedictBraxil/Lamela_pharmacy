package com.kotlin.lamelapharmacy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class About_Us : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_about__us, container, false)

        val aboutTextView: TextView = view.findViewById(R.id.about)
        aboutTextView.text = getString(R.string.about_pharmacy_description)

        return view
    }
}
