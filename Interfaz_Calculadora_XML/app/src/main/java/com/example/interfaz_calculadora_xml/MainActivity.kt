package com.example.interfaz_calculadora_xml

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import net.objecthunter.exp4j.ExpressionBuilder
import org.w3c.dom.Text

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

        val btn0: Button = (findViewById<Button>(R.id.btn0))
        val btn1: Button = (findViewById<Button>(R.id.btn1))
        val btn2: Button = (findViewById<Button>(R.id.btn2))
        val btn3: Button = (findViewById<Button>(R.id.btn3))
        val btn4: Button = (findViewById<Button>(R.id.btn4))
        val btn5: Button = (findViewById<Button>(R.id.btn5))
        val btn6: Button = (findViewById<Button>(R.id.btn6))
        val btn7: Button = (findViewById<Button>(R.id.btn7))
        val btn8: Button = (findViewById<Button>(R.id.btn8))
        val btn9: Button = (findViewById<Button>(R.id.btn9))
        val btnDot: Button = (findViewById<Button>(R.id.btnDot))
        val btnAdd: Button = (findViewById<Button>(R.id.btnAdd))
        val btnSubtract: Button = (findViewById<Button>(R.id.btnSubtract))
        val btnMultiply: Button = (findViewById<Button>(R.id.btnMultiply))
        val btnDivide: Button = (findViewById<Button>(R.id.btnDivide))
        val btnAc: Button = (findViewById<Button>(R.id.btnAc))
        val btnDel: Button = (findViewById<Button>(R.id.btnDel))
        val btnEquals: Button = (findViewById<Button>(R.id.btnEquals))

        addOnClickButton(btn0, textOperation)
        addOnClickButton(btn1, textOperation)
        addOnClickButton(btn2, textOperation)
        addOnClickButton(btn3, textOperation)
        addOnClickButton(btn4, textOperation)
        addOnClickButton(btn5, textOperation)
        addOnClickButton(btn6, textOperation)
        addOnClickButton(btn7, textOperation)
        addOnClickButton(btn8, textOperation)
        addOnClickButton(btn9, textOperation)
        addOnClickButton(btnDot, textOperation)
        addOnClickButton(btnAdd, textOperation)
        addOnClickButton(btnSubtract, textOperation)
        addOnClickButton(btnMultiply, textOperation)
        addOnClickButton(btnDivide, textOperation)

        btnAc.setOnClickListener(){
            textOperation.text = ""
        }

        btnDel.setOnClickListener {
            val currentText = textOperation.text.toString()
            if (currentText.isNotEmpty()) {
                textOperation.text = currentText.dropLast(1)
            }
        }

        btnEquals.setOnClickListener(){
            val text = textOperation.text.toString()
            val result: Double = evaluateExpression(text)
            textResult.text = result.toString()
        }

    }

    fun addOnClickButton(btn: Button, textOperation: TextView){
        btn.setOnClickListener(){
            textOperation.setText(textOperation.text.toString() + btn.text.toString())
        }
    }

    fun evaluateExpression(expression: String): Double {
        return try {
            val expr = ExpressionBuilder(expression).build()
            expr.evaluate()
        } catch (e: Exception) {
            Double.NaN
        }
    }
}