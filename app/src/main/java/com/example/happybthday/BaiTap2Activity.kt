package com.example.happybthday

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.sqrt

class BaiTap2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bai_tap_2)

        val numberInput = findViewById<EditText>(R.id.numberInput)
        val radioEven = findViewById<RadioButton>(R.id.radioEven)
        val radioOdd = findViewById<RadioButton>(R.id.radioOdd)
        val radioSquare = findViewById<RadioButton>(R.id.radioSquare)
        val showButton = findViewById<Button>(R.id.showButton)
        val errorText = findViewById<TextView>(R.id.errorText)
        val resultListView = findViewById<ListView>(R.id.resultListView)
        showButton.setOnClickListener {
            val nText = numberInput.text.toString()
            errorText.text = ""  // Clear error message

            if (nText.isEmpty()) {
                errorText.text = "Vui lòng nhập một số nguyên dương"
                return@setOnClickListener
            }

            if (radioEven.isChecked == false && radioOdd.isChecked == false && radioSquare.isChecked == false) {
                errorText.text = "Vui lòng chọn loại dãy số"
                return@setOnClickListener
            }

            val n = nText.toIntOrNull()
            if (n == null || n <= 0) {
                errorText.text = "Vui lòng nhập một số nguyên dương hợp lệ"
                return@setOnClickListener
            }

            // Xác định danh sách số cần hiển thị
            val resultList = when {
                radioEven.isChecked -> getEvenNumbers(n)
                radioOdd.isChecked -> getOddNumbers(n)
                radioSquare.isChecked -> getSquareNumbers(n)
                else -> emptyList()
            }

            if (resultList.isEmpty()) {
                errorText.text = "Không có số thỏa mãn điều kiện."
            } else {
                // Cập nhật ListView với dữ liệu
                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, resultList)
                resultListView.adapter = adapter
            }
        }
    }

    private fun getEvenNumbers(n: Int): List<Int> {
        val evenNumbers = mutableListOf<Int>()
        for (i in 0..n step 2) {
            evenNumbers.add(i)
        }
        return evenNumbers
    }

    private fun getOddNumbers(n: Int): List<Int> {
        val oddNumbers = mutableListOf<Int>()
        for (i in 1..n step 2) {
            oddNumbers.add(i)
        }
        return oddNumbers
    }

    private fun getSquareNumbers(n: Int): List<Int> {
        val squareNumbers = mutableListOf<Int>()
        var i = 0
        while (i * i <= n) {
            squareNumbers.add(i * i)
            i++
        }
        return squareNumbers
    }
}
