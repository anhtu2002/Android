package com.example.happybthday

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BaiTap3Activity : AppCompatActivity() {

    private lateinit var studentAdapter: StudentAdapter
    private lateinit var studentList: List<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bai_tap_3)

        // Khởi tạo danh sách sinh viên
        studentList = listOf(
            Student("Nguyen Van A", "20120001"),
            Student("Tran Thi B", "20120002"),
            Student("Le Van C", "20120003"),
            // Thêm nhiều sinh viên khác
        )

        // Thiết lập RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.studentRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        studentAdapter = StudentAdapter(studentList)
        recyclerView.adapter = studentAdapter

        // Thiết lập ô tìm kiếm
        val searchEditText = findViewById<EditText>(R.id.searchEditText)
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString().trim()

                if (query.length > 2) {
                    // Tìm kiếm khi từ khóa > 2 ký tự
                    val filteredList = studentList.filter {
                        it.name.contains(query, ignoreCase = true) ||
                                it.studentId.contains(query)
                    }
                    studentAdapter.updateList(filteredList)
                } else {
                    // Hiển thị toàn bộ danh sách nếu từ khóa <= 2 ký tự
                    studentAdapter.updateList(studentList)
                }
            }
        })
    }
}
