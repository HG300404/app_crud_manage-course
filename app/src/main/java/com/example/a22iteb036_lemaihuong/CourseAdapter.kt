package com.example.a22iteb036_lemaihuong

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a22iteb036_lemaihuong.Database.CourseModel

class CourseAdapter(private var courses: List<CourseModel>): RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CourseViewHolder  {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_course,parent,false)
        return CourseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val currentItem = courses[position]
        holder.bind(currentItem)

        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(position)
        }

    }

    override fun getItemCount(): Int {
        return courses.size
    }

    inner class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
       // val id = itemView.findViewById<TextView>(R.id.txtID)
        val nameTextView: TextView = itemView.findViewById(R.id.txtName)
        val amountTextView: TextView = itemView.findViewById(R.id.txtNumber)
        val semesterTextView: TextView = itemView.findViewById(R.id.txtSemester)

        fun bind(course: CourseModel) {
       //     id.text = course.id.toString()
            nameTextView.text = course.name
            amountTextView.text = course.amount.toString()
            semesterTextView.text = course.semester
        }

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    itemClickListener?.onItemClick(position)
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private var itemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

}