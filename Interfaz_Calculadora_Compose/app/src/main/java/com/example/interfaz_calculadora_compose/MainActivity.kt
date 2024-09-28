package com.example.interfaz_calculadora_compose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.objecthunter.exp4j.ExpressionBuilder
import com.example.interfaz_calculadora_compose.ui.theme.Interfaz_Calculadora_ComposeTheme
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

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

// CONTROL DE LA ORIENTACIÓN
@Composable
fun Calculadora() {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    CalculadoraLayout(isLandscape)
}

// LAYOUT CON VALORES DIFERENTES DEPENDIENDO DE LA ORIENTACIÓN
@Composable
fun CalculadoraLayout(
    isLandscape: Boolean
) {
    var textOperation by rememberSaveable { mutableStateOf("") }
    var textResult by rememberSaveable { mutableStateOf("") }

    // Imortación de colores
    val darkBlue = colorResource(R.color.dark_blue)
    val lightBlue = colorResource(R.color.light_blue)
    val mediumBlue = colorResource(R.color.medium_blue)
    val lightOrange = colorResource(R.color.light_orange)
    val darkOrange = colorResource(R.color.dark_orange)
    val white = colorResource(R.color.white)

    // Importación de textos
    val textBtn0 = stringResource(R.string.btn0)
    val textBtn1 = stringResource(R.string.btn1)
    val textBtn2 = stringResource(R.string.btn2)
    val textBtn3 = stringResource(R.string.btn3)
    val textBtn4 = stringResource(R.string.btn4)
    val textBtn5 = stringResource(R.string.btn5)
    val textBtn6 = stringResource(R.string.btn6)
    val textBtn7 = stringResource(R.string.btn7)
    val textBtn8 = stringResource(R.string.btn8)
    val textBtn9 = stringResource(R.string.btn9)
    val textBtnAdd = stringResource(R.string.btnAdd)
    val textBtnSubtract = stringResource(R.string.btnSubtract)
    val textBtnMultiply = stringResource(R.string.btnMultiply)
    val textBtnDivide = stringResource(R.string.btnDivide)
    val textBtnOpen = stringResource(R.string.btnOpen)
    val textBtnClose = stringResource(R.string.btnClose)
    val textBtnAc = stringResource(R.string.btnAc)
    val textBtnDot = stringResource(R.string.btnDot)
    val textBtnEquals = stringResource(R.string.btnEquals)
    val textBtnDel = stringResource(R.string.btnDel)
    val textError = stringResource(R.string.textError)

    // LISTADO Y ORDEN DE LOS BOTONES PARA MODO HORIZONTAL
    val buttonRowsLandscape = listOf(
        listOf(
            Triple(textBtn7, mediumBlue, 1f),
            Triple(textBtn8, mediumBlue, 1f),
            Triple(textBtn9, mediumBlue, 1f),
            Triple(textBtnDel, lightOrange, 1f),
            Triple(textBtnAc, lightOrange, 2.05f)
        ), listOf(
            Triple(textBtn4, mediumBlue, 1f),
            Triple(textBtn5, mediumBlue, 1f),
            Triple(textBtn6, mediumBlue, 1f),
            Triple(textBtnDivide, darkOrange, 1f),
            Triple(textBtnMultiply, darkOrange, 1f),
            Triple(textBtnOpen, lightBlue, 1f)
        ), listOf(
            Triple(textBtn1, mediumBlue, 1f),
            Triple(textBtn2, mediumBlue, 1f),
            Triple(textBtn3, mediumBlue, 1f),
            Triple(textBtnSubtract, darkOrange, 1f),
            Triple(textBtnAdd, darkOrange, 1f),
            Triple(textBtnClose, lightBlue, 1f)
        ), listOf(
            Triple(textBtn0, mediumBlue, 1f),
            Triple(textBtnDot, lightBlue, 1f),
            Triple(textBtnEquals, lightOrange, 1f)
        )
    );

    // LISTADO Y ORDEN DE LOS BOTONES PARA MODO VERTICAL
    val buttonRowsPortrait = listOf(
        listOf(
            Triple(textBtnAc, lightOrange, 3.05f),
            Triple(textBtnDel, lightOrange, 1f)
        ), listOf(
            Triple(textBtnOpen, lightBlue, 1f),
            Triple(textBtnClose, lightBlue, 1f),
            Triple(textBtnDot, lightBlue, 1f),
            Triple(textBtnDivide, darkOrange, 1f)
        ), listOf(
            Triple(textBtn7, mediumBlue, 1f),
            Triple(textBtn8, mediumBlue, 1f),
            Triple(textBtn9, mediumBlue, 1f),
            Triple(textBtnMultiply, darkOrange, 1f)
        ), listOf(
            Triple(textBtn4, mediumBlue, 1f),
            Triple(textBtn5, mediumBlue, 1f),
            Triple(textBtn6, mediumBlue, 1f),
            Triple(textBtnSubtract, darkOrange, 1f)
        ), listOf(
            Triple(textBtn1, mediumBlue, 1f),
            Triple(textBtn2, mediumBlue, 1f),
            Triple(textBtn3, mediumBlue, 1f),
            Triple(textBtnAdd, darkOrange, 1f)
        ), listOf(
            Triple(textBtn0, mediumBlue, 1f),
            Triple(textBtnEquals, lightOrange, 1f)
        )
    );

    val buttonRows = if (isLandscape) buttonRowsLandscape else buttonRowsPortrait

    fun onBtnClick(text: String) {
        when (text) {
            textBtnAc -> {
                textOperation = ""
                textResult = ""
            }
            textBtnDel -> if (textOperation.isNotEmpty()) textOperation = textOperation.dropLast(1)
            textBtnEquals -> {
                textResult =
                    if (evaluateExpression(changeOperators(textOperation)).isNaN()) textError
                    else evaluateExpression(changeOperators(textOperation)).toString()
            }
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
                .padding(if (isLandscape) 60.dp else 20.dp, 10.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp)
                    .weight(if (isLandscape) 0.75f else 1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = textOperation, color = white, style = TextStyle(fontSize = 40.sp))
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 5.dp)
                    .weight(if (isLandscape) 0.5f else 0.75f),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(text = textResult, color = white, style = TextStyle(fontSize = 30.sp))
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier
                    .weight(if (isLandscape) 3f else 4f)
                    .padding(0.dp, 10.dp)
            ) {
                ButtonGrid(buttonRows, ::onBtnClick)
            }
        }
    }
}

// COMPONENTE CON FILAS Y BOTONES
@Composable
fun ButtonGrid(buttonRows: List<List<Triple<String, Color, Float>>>, onBtnClick: (String) -> Unit) {
    for (row in buttonRows) {
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            for ((buttonText, buttonColor, buttonWeight) in row) {
                ModifiedButton(
                    text = buttonText,
                    color = buttonColor,
                    modifier = Modifier.weight(buttonWeight),
                    onButtonClick = onBtnClick
                )
            }
        }
    }
}

// COMPONENTE BOTÓN CON MODIFICACIONES
@Composable
fun ModifiedButton(
    text: String, color: Color, modifier: Modifier = Modifier, onButtonClick: (String) -> Unit
) {
    val buttonPadding: Int =
        if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE) 5 else 15

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
                .padding(0.dp, buttonPadding.dp)
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

fun changeOperators(expression: String): String {
    var newExpression: String = ""
    newExpression = expression.replace('×', '*').replace('÷', '/')
    return newExpression
}

@Preview(showBackground = true)
@Composable
fun CalculadoraPreview() {
    Interfaz_Calculadora_ComposeTheme {
        Calculadora()
    }
}