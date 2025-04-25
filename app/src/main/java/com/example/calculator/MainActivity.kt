package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.cos
import kotlin.math.log10
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private var currentInput: String = ""
    private var operator: String? = null
    private var firstNumber: Double? = null
    private var secondNumber: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.textView)

        // Number buttons (dynamically mapped to all numbers 0-9)
        val numberButtons = listOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3,
            R.id.button4, R.id.button5, R.id.button6, R.id.button7,
            R.id.button8, R.id.button9,
        )

        // Loop to set click listeners for number buttons dynamically
        numberButtons.forEach { id ->
            findViewById<Button>(id).setOnClickListener {
                val number = (it as Button).text.toString()
                appendNumber(number)
            }
        }

        // Operator buttons
        val buttonPlus: Button = findViewById(R.id.buttonplus)
        buttonPlus.setOnClickListener { setOperator("+") }

        val buttonMinus: Button = findViewById(R.id.buttonminus)
        buttonMinus.setOnClickListener { setOperator("-") }

        val buttonMultiply: Button = findViewById(R.id.buttonmultiply)
        buttonMultiply.setOnClickListener { setOperator("*") }

        val buttonDivide: Button = findViewById(R.id.buttondivide)
        buttonDivide.setOnClickListener { setOperator("/") }

        val buttonEquals: Button = findViewById(R.id.buttonequals)
        buttonEquals.setOnClickListener { calculateResult() }

        val buttonPower: Button = findViewById(R.id.buttonpow)
        buttonPower.setOnClickListener { setOperator("pow") }

        val buttonSqrt: Button = findViewById(R.id.buttonsqrt)
        buttonSqrt.setOnClickListener { setOperator("sqrt") }
        //log and trignomentric functions
        val buttonSin: Button = findViewById(R.id.buttonsin)
        buttonSin.setOnClickListener { setOperator("sin") }

        val buttonCos: Button = findViewById(R.id.buttoncos)
        buttonCos.setOnClickListener { setOperator("cos") }

        val buttonTan: Button = findViewById(R.id.buttontan)
        buttonTan.setOnClickListener { setOperator("tan") }

        val buttonLog: Button = findViewById(R.id.buttonlog)
        buttonLog.setOnClickListener { setOperator("log") }


        // Clear button for resetting the calculator
        val buttonClear: Button = findViewById(R.id.buttonAC)
        buttonClear.setOnClickListener { resetCalculator() }

        // C Button to clear last input value
        val buttonClearLast: Button = findViewById(R.id.buttonC)
        buttonClearLast.setOnClickListener { clearLastInput() }

        val buttonModulus: Button = findViewById(R.id.buttonmod)
        buttonModulus.setOnClickListener { setOperator("%") }
    }

    // Function to append numbers to the current input
    private fun appendNumber(number: String) {
        currentInput += number
        display.text = currentInput
    }

    // Function to set the operator (like +, -, *, /, ^, √)
    private fun setOperator(op: String) {
        if (firstNumber == null) {
            firstNumber = currentInput.toDouble()
            currentInput = ""
        }
        operator = op
        display.text = operator
    }

    // Function to calculate the result
    private fun calculateResult() {
        if (firstNumber != null && operator != null) {
            when (operator) {
                "+" -> {
                    secondNumber = currentInput.toDouble()
                    val result = firstNumber!! + secondNumber!!
                    display.text = result.toString()
                }
                "-" -> {
                    secondNumber = currentInput.toDouble()
                    val result = firstNumber!! - secondNumber!!
                    display.text = result.toString()
                }
                "*" -> {
                    secondNumber = currentInput.toDouble()
                    val result = firstNumber!! * secondNumber!!
                    display.text = result.toString()
                }
                "/" -> {
                    secondNumber = currentInput.toDouble()
                    val result = if (secondNumber != 0.0) firstNumber!! / secondNumber!! else Double.NaN
                    display.text = result.toString()
                }
                "pow" -> {  // Power operation (exponentiation)
                    secondNumber = currentInput.toDouble()
                    val result = Math.pow(firstNumber!!, secondNumber!!)
                    display.text = result.toString()
                }
                "sqrt" -> {  // Square Root operation
                    val result = sqrt(firstNumber!!)
                    display.text = result.toString()
                }
                "sin" -> {  // Sine operation (in radians)
                    val result = sin(Math.toRadians(firstNumber!!))
                    display.text = result.toString()
                }
                "cos" -> {  // Cosine operation (in radians)
                    val result = cos(Math.toRadians(firstNumber!!))
                    display.text = result.toString()
                }
                "tan" -> {  // Tangent operation (in radians)
                    val result = tan(Math.toRadians(firstNumber!!))
                    display.text = result.toString()
                }
                "log" -> {  // Logarithm operation (base 10)
                    val result = log10(firstNumber!!)
                    display.text = result.toString()
                }
                "%" -> {  // Modulus operation
                    secondNumber = currentInput.toDouble()
                    val result = firstNumber!! % secondNumber!!
                    display.text = result.toString()
                }
                else -> display.text = "Error"
            }
            //resetCalculator() // Reset after showing the result
        }
    }

    // Function to reset calculator for next calculation
    private fun resetCalculator() {
        firstNumber = null
        secondNumber = null
        currentInput = ""
        operator = null
        display.text = "0"  // Reset the display text to "0"
    }

    // Function to clear the last digit or value from the current input
    private fun clearLastInput() {
        // Clears the last character from the current input (from right to left)
        if (currentInput.isNotEmpty()) {
            currentInput = currentInput.dropLast(1) // Remove last character
            // Update the display to reflect the current input (or "0" if input is empty)
            display.text = if (currentInput.isNotEmpty()) currentInput else "0"
        }
    }

}
