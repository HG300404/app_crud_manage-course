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

class bai2Fragment : Fragment() {

    private var txtA: EditText? = null
    private var txtB: EditText? = null
    private var txtAns2: TextView? = null
    private var btnSolve: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_bai2, container, false)
        txtA = view.findViewById(R.id.edtA)
        txtB = view.findViewById(R.id.edtB)
        txtAns2 = view.findViewById(R.id.txtAns2)
        btnSolve = view.findViewById(R.id.btnSolve)

        btnSolve?.setOnClickListener {
            val txtA = txtA?.text.toString().toDoubleOrNull()
            val txtB = txtB?.text.toString().toDoubleOrNull()

            if (txtA != null && txtB != null) {
                solve(txtA,txtB)
            } else {
                txtAns2?.text = "Invalid input"
            }
        }
        return view
    }

    private fun solve(a: Double,b: Double) {
       if (a == 0.0)
           txtAns2?.text = "The equation has no solution"
        else
       {
           val ans = b / a
           val roundedAns = String.format("%.2f", ans)
           txtAns2?.text = "The equation has solution: $roundedAns "
       }
    }
}