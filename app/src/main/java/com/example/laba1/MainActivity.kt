package com.example.laba1

import android.os.Bundle
//import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var inputElement: EditText
    private lateinit var inputValue: EditText
    private lateinit var calculateButton: Button
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputElement = findViewById(R.id.inputElement)
        inputValue = findViewById(R.id.inputValue)
        calculateButton = findViewById(R.id.calculateButton)
        resultTextView = findViewById(R.id.resultView)

        calculateButton.setOnClickListener { calculate() }
    }

    private fun calculate() {
        val element = inputElement.text.toString().toLowerCase()
        val valueString = inputValue.text.toString()

        if (valueString.isNotEmpty()) {
            val value = valueString.toDoubleOrNull()

            if (value != null) {
                when (element) {
                    "c" -> calculateFromCathetus(value)
                    "g" -> calculateFromHypotenuse(value)
                    "s" -> calculateFromArea(value)
                    else -> resultTextView.text = "Неверный элемент. Используйте 'c', 'g' или 's'."
                }
            } else {
                resultTextView.text = "Некорректное значение."
            }
        } else {
            resultTextView.text = "Поле значения пустое."
        }
    }

    private fun calculateFromCathetus(cathetus: Double) {
        val hypotenuse = cathetus * Math.sqrt(2.0)
        val area = (cathetus * cathetus) / 2
        resultTextView.text = "Гипотенуза: $hypotenuse\nПлощадь: $area"
    }

    private fun calculateFromHypotenuse(hypotenuse: Double) {
        val cathetus = hypotenuse / Math.sqrt(2.0)
        val area = (cathetus * cathetus) / 2
        resultTextView.text = "Катет: $cathetus\nПлощадь: $area"
    }

    private fun calculateFromArea(area: Double) {
        val cathetus = Math.sqrt(area * 2)
        val hypotenuse = cathetus * Math.sqrt(2.0)
        resultTextView.text = "Катет: $cathetus\nГипотенуза: $hypotenuse"
    }
}
}