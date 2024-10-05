package com.example.interfaz_calculadora_xml

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import net.objecthunter.exp4j.ExpressionBuilder

/**
 * Actividad principal de la aplicación de la calculadora.
 *
 */
class MainActivity : AppCompatActivity() {
    // Se aplican a sus respectivos campos y se guarda su valor
    private var operation: String = ""
    private var result: String = ""

    /**
     * Método llamado cuando la actividad se crea por primera vez.
     *
     * @param savedInstanceState Bundle que contiene el estado guardado de la actividad, si está disponible.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textOperation = (findViewById<TextView>(R.id.textOperation))
        val textResult = (findViewById<TextView>(R.id.textResult))

        // Restaurar el estado si existe
        if (savedInstanceState != null) {
            operation = savedInstanceState.getString("operation", "")
            result = savedInstanceState.getString("result", "")
            textOperation.text = operation
            textResult.text = result
        }

        // IDs de los botones numéricos, paréntesis, coma y de operadores
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
                operation += btn.text.toString()
                textOperation.text = operation
            }
        }

        // Botón que resetea el campo de operación
        (findViewById<Button>(R.id.btnAc)).setOnClickListener(){
            operation = ""
            result = ""
            textOperation.text = operation
            textResult.text = result
        }

        // Botón que borra el último caracter introducido en el campo de operación
        (findViewById<Button>(R.id.btnDel)).setOnClickListener {
            if (operation.isNotEmpty()) {
                operation = operation.dropLast(1)
                textOperation.text = operation
            }
        }

        // Botón que envía la expresión a ser evaluada
        (findViewById<Button>(R.id.btnEquals)).setOnClickListener(){
            if (operation.isNotEmpty()){
                val resultado: Double = evaluarOperacion(cambiarCaracteres(operation))
                // Comprobar si el resultado es válido o es NaN
                result = if (resultado.isNaN()) getString(R.string.textError) else cambiarCaracteresResultado(resultado.toString())
                textResult.text = result
            }
        }

    }

    /**
     * Guarda el estado de la instancia de la actividad.
     *
     * @param outState El `Bundle` en el que se deben guardar los estados de la actividad.
     * Se utiliza para almacenar datos que se pueden necesitar para restaurar la actividad.
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("operation", operation)
        outState.putString("result", result)
    }

    /**
     * Evalúa la expresión mediante la librería exp4j.
     *
     * @param expresion String con la expresión a evaluar.
     * @return Resultado de la expresión como Double en caso de que pueda llevarse a cabo y Double.NaN en caso contrario.
     */
    private fun evaluarOperacion(expresion: String): Double {
        return try {
            val expr = ExpressionBuilder(expresion).build()
            expr.evaluate()
        } catch (e: Exception) {
            Double.NaN
        }
    }

    /**
     * Cambia los operadores que se muestran en la calculadora, por operadores que puedan ser interpretados correctamente por exp4j.
     *
     * @param expresion String que contiene la expresión a ser evaluada y en la cual se realizará el reemplazo de operadores.
     * @return String de la expresión con los operadores cambiados.
     */
    private fun cambiarCaracteres(expresion: String): String {
        val expresionValida = expresion.replace('×', '*').replace('÷', '/').replace(',', '.')
        return expresionValida
    }

    /**
     * Cambia los puntos por las comas en una cadena.
     *
     * @param expresion String en el que se realiza el reemplazo de caracteres.
     * @return String con comas.
     */
    private fun cambiarCaracteresResultado(resultado: String): String {
        val resultadoConComa = resultado.replace('.', ',')
        return resultadoConComa
    }
}