package com.example.a22iteb036_lemaihuong

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a22iteb036_lemaihuong.Database.CourseModel
import com.example.a22iteb036_lemaihuong.databinding.ActivityInsertBinding
import com.example.a22iteb036_lemaihuong.fragments.bai3Fragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class InsertActivity : AppCompatActivity() {
    private lateinit var dbRef : DatabaseReference
    private lateinit var binding: ActivityInsertBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbRef = FirebaseDatabase.getInstance().getReference("Courses")

        binding.btnSave.setOnClickListener {
            saveCourse()
        }
    }

    private fun saveCourse() {
        //getting value
        val name = binding.edtName.text.toString()
        val amount = binding.edtAmount.text.toString()
        val semester = binding.edtSemester.text.toString()
        //push data
        val id = dbRef.push().key!!
        val course = CourseModel(id,name,amount,semester)

        if (name.isEmpty()){
            binding.edtName.error = "Please enter name"
            return
        }
        if (amount.isEmpty()){
            binding.edtAmount.error = "Please enter amount"
            return
        }
        if (semester.isEmpty()){
            binding.edtSemester.error = "Please enter semester"
            return
        }
        dbRef.child(id).setValue(course)
            .addOnCompleteListener{
                Toast.makeText(this,"Successfully added!!",Toast.LENGTH_SHORT).show()
                binding.edtName.setText("")
                binding.edtAmount.setText("")
                binding.edtSemester.setText("")
                val intent = Intent(this, bai3Fragment::class.java)
                startActivity(intent)
            }
            .addOnFailureListener {
                Toast.makeText(this,"Error!!",Toast.LENGTH_SHORT).show()
            }
    }
}