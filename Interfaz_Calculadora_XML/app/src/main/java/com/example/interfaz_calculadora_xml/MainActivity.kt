package com.example.interfaz_calculadora_xml

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textOperation: TextView = (findViewById<TextView>(R.id.textOperation))
        val textResult: TextView = (findViewById<TextView>(R.id.textResult))

        // IDs de los botones numéricos y de operación
        val buttons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
            R.id.btnDot, R.id.btnAdd, R.id.btnSubtract, R.id.btnMultiply, R.id.btnDivide,
            R.id.btnOpen, R.id.btnClose
        )

        // Configurar onClick para que se añada el caracter que contiene cada uno al TextView
        buttons.forEach { buttonId ->
            val btn: Button = findViewById<Button>(buttonId)
            btn.setOnClickListener(){
                textOperation.text = textOperation.text.toString() + btn.text.toString()
            }
        }

        // Botón que resetea el campo de operación
        (findViewById<Button>(R.id.btnAc)).setOnClickListener(){
            textOperation.text = ""
        }

        // Botón que borra el último caracter introducido en el campo de operación
        (findViewById<Button>(R.id.btnDel)).setOnClickListener {
            val currentText = textOperation.text.toString()
            if (currentText.isNotEmpty()) {
                textOperation.text = currentText.dropLast(1)
            }
        }

        // Botón que envía la expresión a ser evaluada
        (findViewById<Button>(R.id.btnEquals)).setOnClickListener(){
            val expression = textOperation.text.toString()
            val result: Double = evaluateExpression(changeOperators(expression))
            // Comprobar si el resultado es válido o es NaN
            if (result.isNaN()) {
                textResult.text = getString(R.string.textError)
            } else {
                textResult.text = result.toString()
            }
        }

    }

    // Utilización de la librería Rhino para evaluar una expresión matemática y devolver el valor
    // Si no puede evaluar la expresión devuelve NaN (Not a Number)
    fun evaluateExpression(expression: String): Double {
        return try {
            val expr = ExpressionBuilder(expression).build()
            expr.evaluate()
        } catch (e: Exception) {
            Double.NaN
        }
    }

    // En los botones de los operadores de multiplicación y división es necesario cambiar el caracter para que funcione Rhino
    fun changeOperators(expression: String) : String {
        var newExpression: String = ""
        newExpression = expression.replace('×', '*').replace('÷', '/')
        return newExpression
    }
}