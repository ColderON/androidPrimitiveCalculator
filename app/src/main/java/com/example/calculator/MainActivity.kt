package com.example.calculator

import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {

    private lateinit var etResult: EditText

    private var btnZero: Button? = null
    private var btnOne: Button? = null
    private var btnTwo: Button? = null
    private var btnThree: Button? = null
    private var btnFour: Button? = null
    private var btnFive: Button? = null
    private var btnSix: Button? = null
    private var btnSeven: Button? = null
    private var btnEight: Button? = null
    private var btnNine: Button? = null
    private var btnDivide: Button? = null
    private var btnMultiple: Button? = null
    private var btnMinus: Button? = null
    private var btnPlus: Button? = null


    private var btnClear: Button? = null
    private var btnBack: Button? = null
    private var btnEqual: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etResult = findViewById(R.id.etResult)


        btnOne = findViewById(R.id.btn1) as Button
        btnTwo = findViewById(R.id.btn2) as Button
        btnThree = findViewById(R.id.btn3) as Button
        btnFour = findViewById(R.id.btn4) as Button
        btnFive = findViewById(R.id.btn5) as Button
        btnSix = findViewById(R.id.btn6) as Button
        btnSeven = findViewById(R.id.btn7) as Button
        btnEight = findViewById(R.id.btn8) as Button
        btnNine = findViewById(R.id.btn9) as Button
        btnZero = findViewById(R.id.btnZero) as Button

        btnDivide = findViewById(R.id.btnDivide) as Button
        btnMultiple = findViewById(R.id.btnMultiple) as Button
        btnMinus = findViewById(R.id.btnMinus) as Button
        btnPlus = findViewById(R.id.btnPlus) as Button

        btnClear = findViewById(R.id.btnClear) as Button
        btnBack = findViewById(R.id.btnBack) as Button
        btnEqual = findViewById(R.id.btnEqual) as Button
    }

    fun onDigit(view: View) {

        if (view is Button) {
            val buttonValue = view.text.toString()
            val resultText = etResult.text.toString()

            Log.d(TAG, "buttonValue  "+ buttonValue)

            // Проверяем, является ли последний символ в тексте операцией, и добавляем число или операцию
            if (resultText.isEmpty() && (buttonValue == "+" || buttonValue == "-" || buttonValue == "*" || buttonValue == "÷")) {
                // Не добавляем операцию в начале
                return
            } else if (resultText.isNotEmpty()) {
                val lastChar = resultText.last()

                Log.d(TAG, "lastChar "+ lastChar)

                if ((lastChar == '+' || lastChar == '-' || lastChar == '*' || lastChar == '÷') && (buttonValue == "+" || buttonValue == "-" || buttonValue == "*" || buttonValue == "÷")) {

                    Log.d(TAG, "last Char was Symbol " + lastChar)

                    etResult.text = resultText.dropLast(1).toEditable()
                }
            }

            etResult.text.append(buttonValue)
            Log.d(TAG, "etResult "+etResult.text.toString());
        }
    }
    private fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

    fun onRemoveLast(view: View) {
        val resultText = etResult.text.toString()
        if (resultText.isNotEmpty()) {
            etResult.text = resultText.dropLast(1).toEditable()
        }
    }

    fun onClear(view: View) {
        etResult.setText("")
    }

    fun performOperation(view: View) {

        val expression = etResult.text.toString()
        if (expression.isEmpty()) return

        val regex = Regex("([-*+÷])")
        val parts = expression.split(regex).filterNot { it.isEmpty() }
        val operators = regex.findAll(expression).map { it.value }.toList()

        if (parts.size < 2) return

        try {
            var result = parts[0].toDouble()
            for (i in 1 until parts.size) {
                when (operators[i - 1]) {
                    "+" -> result += parts[i].toDouble()
                    "-" -> result -= parts[i].toDouble()
                    "*" -> result *= parts[i].toDouble()
                    "÷" -> result /= parts[i].toDouble()
                }
            }
            etResult.setText(result.toString())

        } catch (e: Exception) {
            etResult.setText("Error")
        }
    }

}






// Функция для выполнения математических операций
    private fun performOperation() {
        // Добавьте логику вычисления здесь
    }



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CalculatorTheme {
        Greeting("Android")
    }
}