package com.example.interfaz_calculadora_compose

import android.os.Bundle
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.interfaz_calculadora_compose.ui.theme.Interfaz_Calculadora_ComposeTheme

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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(darkBlue)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp)
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Operation", color = white, style = TextStyle(fontSize = 30.sp))
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp)
                    .weight(0.75f),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text("Result", color = white, style = TextStyle(fontSize = 20.sp))
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier
                    .weight(4f)
                    .padding(0.dp, 20.dp),
            ) {
                ButtonRow(
                    buttonTexts = listOf("AC", "⌫"),
                    buttonWeights = listOf(3f, 1f),
                    buttonColors = listOf(lightOrange, lightOrange)
                )

                ButtonRow(
                    buttonTexts = listOf("(", ")", ".", "÷"),
                    buttonWeights = listOf(1f, 1f, 1f, 1f),
                    buttonColors = listOf(mediumBlue, mediumBlue, mediumBlue, darkOrange)
                )

                ButtonRow(
                    buttonTexts = listOf("7", "8", "9", "×"),
                    buttonWeights = listOf(1f, 1f, 1f, 1f),
                    buttonColors = listOf(mediumBlue, mediumBlue, mediumBlue, darkOrange)
                )

                ButtonRow(
                    buttonTexts = listOf("4", "5", "6", "-"),
                    buttonWeights = listOf(1f, 1f, 1f, 1f),
                    buttonColors = listOf(mediumBlue, mediumBlue, mediumBlue, darkOrange)
                )

                ButtonRow(
                    buttonTexts = listOf("1", "2", "3", "+"),
                    buttonWeights = listOf(1f, 1f, 1f, 1f),
                    buttonColors = listOf(mediumBlue, mediumBlue, mediumBlue, darkOrange)
                )

                ButtonRow(
                    buttonTexts = listOf("0", "="),
                    buttonWeights = listOf(1f, 1f),
                    buttonColors = listOf(mediumBlue, lightOrange)
                )
            }
        }
    }
}

// Crea una fila de botones con su texto, ancho y color
@Composable
fun ButtonRow(
    buttonTexts: List<String>,
    buttonWeights: List<Float>,
    buttonColors: List<Color>
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(5.dp), // Espacio entre los botones de la fila
        verticalAlignment = Alignment.CenterVertically
    ) {
        /*
        * forEachIndexed es una función en Kotlin que itera sobre una colección (como una lista)
        * y permite acceder tanto al índice de cada elemento como al propio elemento durante
        * la iteración.
        */
        buttonTexts.forEachIndexed { index, text ->
            Button(
                onClick = { /* TODO */ }, // Asignar la función onClick
                modifier = Modifier
                    .weight(buttonWeights[index]), // Controla el ancho
                colors = ButtonDefaults.buttonColors(containerColor = buttonColors[index]), // Aplicar el color de fondo que se pasa como parámetro
                shape = RoundedCornerShape(5.dp) // Controlar el borde
            ) {
                Text(
                    text,
                    style = TextStyle(fontSize = 30.sp),
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically), // Alineamiento vertical
                    textAlign = TextAlign.Center  // Alineamiento horizontal
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Interfaz_Calculadora_ComposeTheme {
        Calculadora()
    }
}