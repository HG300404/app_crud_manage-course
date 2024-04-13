package com.example.a22iteb036_lemaihuong.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.a22iteb036_lemaihuong.R

class bai1Fragment : Fragment() {

    private var txtNumber: EditText? = null
    private var txtAns: TextView? = null
    private var btnCheck: Button? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_bai1, container, false)
        txtNumber = view.findViewById(R.id.edtNumber)
        btnCheck = view.findViewById(R.id.btCheck)
        txtAns = view.findViewById(R.id.txtAns)

        btnCheck?.setOnClickListener {
            val number = txtNumber?.text.toString().toIntOrNull()
            if (number != null){
                if (checkPrimeNumber(number)){
                    txtAns?.text = "$number is a prime number"
                } else {
                    txtAns?.text = "$number is not a prime number"
                }
            } else {
                txtAns?.text = "Invalid input"
            }
        }
        return view
    }

    private fun checkPrimeNumber(number: Int): Boolean {
        if (number <= 1) {
            return false
        }

        for (i in 2 until number) {
            if (number % i == 0) {
                return false
            }
        }

        return true
    }

}