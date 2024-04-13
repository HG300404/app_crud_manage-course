package com.example.a22iteb036_lemaihuong.fragments

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.ContextMenu
import android.view.ContextMenu.ContextMenuInfo
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a22iteb036_lemaihuong.CourseAdapter
import com.example.a22iteb036_lemaihuong.Database.CourseModel
import com.example.a22iteb036_lemaihuong.EditActivity
import com.example.a22iteb036_lemaihuong.InsertActivity
import com.example.a22iteb036_lemaihuong.R
import com.example.a22iteb036_lemaihuong.databinding.FragmentBai3Binding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.json.JSONObject

class bai3Fragment : Fragment() {
    private lateinit var binding: FragmentBai3Binding
    private lateinit var courseAdapter: CourseAdapter
    private lateinit var list: ArrayList<CourseModel>
    private lateinit var dbRef: DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentBai3Binding.inflate(inflater, container, false)


        binding.recycleView.layoutManager = LinearLayoutManager(requireContext())
        binding.recycleView.setHasFixedSize(true)

        list = arrayListOf<CourseModel>()

//        registerForContextMenu(binding.recycleView)

        getData()

        binding.btAdd.setOnClickListener {
            val intent = Intent(activity, InsertActivity::class.java)
            startActivity(intent)
        }

        binding.btSearch.setOnClickListener {
            search()
        }
        return binding.root
    }

    private fun search() {
        dbRef = FirebaseDatabase.getInstance().getReference("Courses")
        val searchText = binding.edtSearch.text.toString()
        val query = dbRef.orderByValue().startAt(searchText).endAt(searchText + "\uf8ff")
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                if (snapshot.exists()) {
                    for (i in snapshot.children) {
                        val data = i.getValue(CourseModel::class.java)
                        list.add(data!!)
                    }
                    courseAdapter = CourseAdapter(list)
                    binding.recycleView.adapter = courseAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun getData() {
        dbRef = FirebaseDatabase.getInstance().getReference("Courses")
        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                if (snapshot.exists()){
                    for (i in snapshot.children){
                        val data = i.getValue(CourseModel::class.java)
                        list.add(data!!)
                    }
                    courseAdapter = CourseAdapter(list)
                    binding.recycleView.adapter = courseAdapter


                    // click item
                    courseAdapter.setOnItemClickListener(object : CourseAdapter.OnItemClickListener{
                        //ctril + i
                        override fun onItemClick(position: Int) {
                            val intent = Intent(activity, EditActivity::class.java)
                            //put extra
                            intent.putExtra("id",list[position].id)
                            intent.putExtra("name",list[position].name)
                            intent.putExtra("amount",list[position].amount)
                            intent.putExtra("semester",list[position].semester)
                            startActivity(intent)
                        }

                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

//    override fun onCreateContextMenu(menu: ContextMenu, view: View, menuInfo: ContextMenuInfo?) {
//        super.onCreateContextMenu(menu, view, menuInfo)
//        activity?.menuInflater?.inflate(R.menu.menu_context, menu)
//    }
//
//    override fun onContextItemSelected(item: MenuItem): Boolean {
//        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
//        val position = info.position
//        val selectedCourse = list[position] // Lấy dữ liệu của mục được chọn
//
//        when (item.itemId) {
//            R.id.menuUpdate -> {
//                val intent = Intent(activity, EditActivity::class.java)
//                // Thêm dữ liệu vào Intent nếu cần
//                // intent.putExtra("courseJson", json.toString())
//                startActivity(intent)
//                return true // Trả về true để cho biết sự kiện đã được xử lý
//            }
//            R.id.menuRemove -> {
//                // Xử lý khi người dùng chọn Delete
//                return true // Trả về true để cho biết sự kiện đã được xử lý
//            }
//            else -> return super.onContextItemSelected(item)
//        }
//    }

}

