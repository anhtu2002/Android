package com.example.happybthday

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import android.widget.Button
import android.widget.TextView
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var textResult: TextView
    private var lastNumeric: Boolean = false
    private var lastDot: Boolean = false
    private var stateError: Boolean = false
    private var currentOperator: Char? = null
    private var operand1: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.caculator_linear_layout)
        textResult = findViewById(R.id.text_result)

        setNumericButtonListeners()
        setOperatorButtonListeners()
        setControlButtonListeners()
    }
    private fun setNumericButtonListeners() {
        val numericButtons = listOf<Button>(
            findViewById(R.id.btn0),
            findViewById(R.id.btn1),
            findViewById(R.id.btn2),
            findViewById(R.id.btn3),
            findViewById(R.id.btn4),
            findViewById(R.id.btn5),
            findViewById(R.id.btn6),
            findViewById(R.id.btn7),
            findViewById(R.id.btn8),
            findViewById(R.id.btn9)
        )

        for (button in numericButtons) {
            button.setOnClickListener {
                if (stateError) {
                    textResult.text = button.text
                    stateError = false
                } else {
                    textResult.append(button.text)
                }
                lastNumeric = true
            }
        }
    }

    private fun setOperatorButtonListeners() {
        val operatorButtons = listOf<Button>(
            findViewById(R.id.btnAdd),
            findViewById(R.id.btn_tru),
            findViewById(R.id.btn_nhan),
            findViewById(R.id.btn_chia)
        )

        for (button in operatorButtons) {
            button.setOnClickListener {
                if (lastNumeric && !stateError) {
                    operand1 = textResult.text.toString()
                    currentOperator = button.text[0]
                    textResult.append(" ${button.text} ")
                    lastNumeric = false
                    lastDot = false
                }
            }
        }
    }
    private fun setControlButtonListeners() {
        findViewById<Button>(R.id.btnEqual).setOnClickListener {
            if (lastNumeric && !stateError) {
                val secondOperand = textResult.text.toString().split(" ").last()
                performCalculation(secondOperand)
            }
        }

        findViewById<Button>(R.id.btnc).setOnClickListener {
            textResult.text = "0"
            lastNumeric = false
            lastDot = false
            stateError = false
        }

        findViewById<Button>(R.id.btnc).setOnClickListener {
            textResult.text = "0"
            lastNumeric = false
            lastDot = false
            stateError = false
        }

        findViewById<Button>(R.id.btnc).setOnClickListener {
            textResult.text = "0"
            lastNumeric = false
            lastDot = false
            stateError = false
        }

        findViewById<Button>(R.id.btnc).setOnClickListener {
            textResult.text = "0"
            lastNumeric = false
            lastDot = false
            stateError = false
        }
    }
    private fun performCalculation(secondOperand: String) {
        try {
            val result = when (currentOperator) {
                '+' -> operand1.toDouble() + secondOperand.toDouble()
                '-' -> operand1.toDouble() - secondOperand.toDouble()
                'x' -> operand1.toDouble() * secondOperand.toDouble()
                '/' -> operand1.toDouble() / secondOperand.toDouble()
                else -> return
            }
            textResult.text = result.toString()
        } catch (e: Exception) {
            textResult.text = "Error"
            stateError = true
        }
    }
}


