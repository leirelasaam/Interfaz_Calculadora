package com.example.interfaz_calculadora_compose

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.interfaz_calculadora_compose.ui.theme.Interfaz_Calculadora_ComposeTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import net.objecthunter.exp4j.ExpressionBuilder

val darkBlue: Color = Color(0xFF021E2C)
val lightBlue: Color = Color(0x808ECAE6)
val mediumBlue: Color = Color(0x80219EBC)
val lightOrange: Color = Color(0x80FFB703)
val darkOrange: Color = Color(0x80FB8500)
val white: Color = Color(0xFFFFFFFF)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Interfaz_Calculadora_ComposeTheme {
                Calculadora()
            }
        }
    }
}

@Composable
fun Calculadora() {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    if (isLandscape) {
        LandscapeLayout()
    } else {
        PortraitLayout()
    }
}

@Composable
fun PortraitLayout() {
    var textOperation by remember { mutableStateOf("") }
    var textResult by remember { mutableStateOf("") }

    fun onBtnClick(text: String) {
        when (text) {
            "AC" -> textOperation = ""
            "⌫" -> if (textOperation.isNotEmpty()) textOperation = textOperation.dropLast(1)
            "=" -> textResult = if (evaluateExpression(changeOperators(textOperation)).isNaN()) "Expresión inválida" else evaluateExpression(changeOperators(textOperation)).toString()
            else -> textOperation += text
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(darkBlue)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp, 0.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp)
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = textOperation, color = white, style = TextStyle(fontSize = 40.sp))
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 5.dp)
                    .weight(0.75f),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(text = textResult, color = white, style = TextStyle(fontSize = 30.sp))
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier
                    .weight(4f)
                    .padding(0.dp, 20.dp)
            ) {
                // IMPORTANTE -> ::nombreFuncion en Kotlin es una forma de referirse a una función sin ejecutarla de inmediato.
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ModifiedButton("AC",  lightOrange, Modifier.weight(3f), ::onBtnClick)
                    ModifiedButton("⌫",  lightOrange, Modifier.weight(0.97f), ::onBtnClick)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ModifiedButton("(",  lightBlue, Modifier.weight(1f), ::onBtnClick)
                    ModifiedButton(")",  lightBlue, Modifier.weight(1f), ::onBtnClick)
                    ModifiedButton(".",  lightBlue, Modifier.weight(1f), ::onBtnClick)
                    ModifiedButton("÷",  darkOrange, Modifier.weight(1f), ::onBtnClick)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ModifiedButton("7",  mediumBlue, Modifier.weight(1f), ::onBtnClick)
                    ModifiedButton("8",  mediumBlue, Modifier.weight(1f), ::onBtnClick)
                    ModifiedButton("9",  mediumBlue, Modifier.weight(1f), ::onBtnClick)
                    ModifiedButton("×",  darkOrange, Modifier.weight(1f), ::onBtnClick)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ModifiedButton("4",  mediumBlue, Modifier.weight(1f), ::onBtnClick)
                    ModifiedButton("5",  mediumBlue, Modifier.weight(1f), ::onBtnClick)
                    ModifiedButton("6",  mediumBlue, Modifier.weight(1f), ::onBtnClick)
                    ModifiedButton("-",  darkOrange, Modifier.weight(1f), ::onBtnClick)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ModifiedButton("1",  mediumBlue, Modifier.weight(1f), ::onBtnClick)
                    ModifiedButton("2",  mediumBlue, Modifier.weight(1f), ::onBtnClick)
                    ModifiedButton("3",  mediumBlue, Modifier.weight(1f), ::onBtnClick)
                    ModifiedButton("+",  darkOrange, Modifier.weight(1f), ::onBtnClick)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ModifiedButton("0",  mediumBlue, Modifier.weight(1f), ::onBtnClick)
                    ModifiedButton("=",  mediumBlue, Modifier.weight(1f), ::onBtnClick)
                }
            }
        }
    }
}

@Composable
fun LandscapeLayout() {
}

@Composable
fun ModifiedButton(
    text: String,
    color: Color,
    modifier: Modifier = Modifier,
    onButtonClick: (String) -> Unit
) {
        Button(
            onClick = { onButtonClick(text) },
            modifier = modifier,
            colors = ButtonDefaults.buttonColors(containerColor = color),
            shape = RoundedCornerShape(5.dp)
        ) {
            Text(
                text,
                style = TextStyle(fontSize = 30.sp),
                modifier = Modifier
                    .padding(0.dp, 15.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.Center
            )

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

fun changeOperators(expression: String) : String {
    var newExpression: String = ""
    newExpression = expression.replace('×', '*').replace('÷', '/')
    return newExpression
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Interfaz_Calculadora_ComposeTheme {
        Calculadora()
    }
}