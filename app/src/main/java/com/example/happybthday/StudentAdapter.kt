package com.example.happybthday

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class StudentAdapter(val students: List<StudentModel>): RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
    class StudentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textStudentName: TextView = itemView.findViewById(R.id.text_student_name)
        val textStudentId: TextView = itemView.findViewById(R.id.text_student_id)
        val imageEdit: ImageView = itemView.findViewById(R.id.image_edit)
        val imageRemove: ImageView = itemView.findViewById(R.id.image_remove)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_student_item,
            parent, false)
        return StudentViewHolder(itemView)
    }

    override fun getItemCount(): Int = students.size

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]

        holder.textStudentName.text = student.studentName
        holder.textStudentId.text = student.studentId
//
        // Xử lý khi nhấn nút Edit
        holder.imageEdit.setOnClickListener {
            // Hiển thị Dialog để cập nhật thông tin sinh viên
            val dialog = AlertDialog.Builder(holder.itemView.context)
            val dialogView = LayoutInflater.from(holder.itemView.context)
                .inflate(R.layout.dialog_add_student, null) // Tạo layout riêng cho Dialog
            dialog.setView(dialogView)
            dialog.setTitle("Chỉnh sửa")

            val editStudentName = dialogView.findViewById<EditText>(R.id.edit_student_name)
            val editStudentId = dialogView.findViewById<EditText>(R.id.edit_student_id)

            editStudentName.setText(student.studentName)
            editStudentId.setText(student.studentId)

            dialog.setPositiveButton("Cập nhật") { _, _ ->
                // Cập nhật thông tin
                student.studentName = editStudentName.text.toString()
                student.studentId = editStudentId.text.toString()
                notifyItemChanged(position)
            }
            dialog.setNegativeButton("Hủy", null)
            dialog.create().show()
        }

        // Xử lý khi nhấn nút Delete
        holder.imageRemove.setOnClickListener {
            val removedStudent = students[position]
            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Xóa?")
                .setMessage("Bạn có chắc muốn xóa?" +
                        "${removedStudent.studentName}")
                .setPositiveButton("Có") { _, _ ->
                    // Xóa sinh viên và hiển thị Snackbar

                    (students as MutableList).removeAt(position)
                    notifyItemRemoved(position)

                    Snackbar.make(holder.itemView, "Đã xóa ${removedStudent.studentName}", Snackbar.LENGTH_LONG)
                        .setAction("Hoàn tác") {
                            (students as MutableList).add(position, removedStudent)
                            notifyItemInserted(position)
                        }
                        .setDuration(10000)
                        .show()
                }
                .setNegativeButton("Không", null)
                .create()
                .show()
        }
    }
}
