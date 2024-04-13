package com.example.a22iteb036_lemaihuong

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.a22iteb036_lemaihuong.Database.CourseModel
import com.example.a22iteb036_lemaihuong.databinding.ActivityEditBinding
import com.example.a22iteb036_lemaihuong.databinding.UpdateDialogBinding
import com.example.a22iteb036_lemaihuong.fragments.bai3Fragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EditActivity : AppCompatActivity() {
    private lateinit var dbRef : DatabaseReference
    private lateinit var binding: ActivityEditBinding
     private lateinit var bindingDialogBinding: UpdateDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setValueToView()

        binding.btRemove.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("id").toString()
            )
        }
        binding.btEdit.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("id").toString(),
                intent.getStringExtra("name").toString()
            )
        }
    }

    private fun openUpdateDialog(id:String,name: String) {
        val dialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.update_dialog,null)
        dialog.setView(dialogView)

        val nameDialog = dialogView.findViewById<EditText>(R.id.edtNameDialog)
        val amountDialog = dialogView.findViewById<EditText>(R.id.edtAmountDialog)
        val semesterDialog = dialogView.findViewById<EditText>(R.id.edtSemesterDialog)
        val btnUpdateDialog = dialogView.findViewById<Button>(R.id.btnSaveDialog)

        nameDialog.setText(intent.getStringExtra("name").toString())
        amountDialog.setText(intent.getStringExtra("amount").toString())
        semesterDialog.setText(intent.getStringExtra("semester").toString())




        dialog.setTitle("CẬP NHẬT DỮ LIỆU $name ")
        val alertDialog = dialog.create()
        alertDialog.show()

        //
        btnUpdateDialog.setOnClickListener {
            updateDialog(
                id,
                nameDialog.text.toString(),
                amountDialog.text.toString(),
                semesterDialog.text.toString()
            )
            Toast.makeText(applicationContext,"Cập nhật thành công",Toast.LENGTH_SHORT).show()
           //
            binding.txtName.setText(nameDialog.text.toString())
            binding.txtAmount.setText(amountDialog.text.toString())
            binding.txtSemester.setText(semesterDialog.text.toString())

            alertDialog.dismiss()
        }
    }

    private fun updateDialog(id: String, nameDialog: String, amountDialog: String, semesterDialog: String) {
        dbRef = FirebaseDatabase.getInstance().getReference("Courses").child(id)
        val info = CourseModel(id,nameDialog,amountDialog,semesterDialog)
        dbRef.setValue(info)

    }

    private fun deleteRecord(id: String) {
        dbRef = FirebaseDatabase.getInstance().getReference("Courses").child(id)
        val task = dbRef.removeValue()
        task.addOnSuccessListener { 
            Toast.makeText(this,"Xoá thành công",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, bai3Fragment::class.java)
//            finish()
            startActivity(intent)
        }.addOnFailureListener { 
            Toast.makeText(this,"Xoá không thành công",Toast.LENGTH_SHORT).show()
        }
    }

    private fun setValueToView() {
        binding.txtID.text = intent.getStringExtra("id")
        binding.txtName.text = intent.getStringExtra("name")
        binding.txtAmount.text = intent.getStringExtra("amount")
        binding.txtSemester.text = intent.getStringExtra("semester")
    }
}
