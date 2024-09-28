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

/**
 * Actividad principal de la aplicación de la calculadora.
 *
 */
class MainActivity : ComponentActivity() {
    /**
     * Método llamado cuando la actividad se crea por primera vez.
     *
     * @param savedInstanceState Bundle que contiene el estado guardado de la actividad, si está disponible.
     */
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

/**
 * Comprueba la orientación del dispositivo.
 *
 */
@Composable
fun Calculadora() {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    CalculadoraLayout(isLandscape)
}

/**
 * Representa el diseño de la interfaz que se adapta a la orientación del dispositivo.
 *
 * @param isLandscape Un booleano que indica si la orientación de la pantalla es horizontal (true) o vertical (false).
 */
@Composable
fun CalculadoraLayout(
    isLandscape: Boolean
) {
    // Variable para controlar el texto de operación y guardar su estado
    var textOperation by rememberSaveable { mutableStateOf("") }
    // Variable para controlar el texto del resultado y guardar su estado
    var textResult by rememberSaveable { mutableStateOf("") }

    // Imortación de colores del archivo colors.xml
    val darkBlue = colorResource(R.color.dark_blue)
    val lightBlue = colorResource(R.color.light_blue)
    val mediumBlue = colorResource(R.color.medium_blue)
    val lightOrange = colorResource(R.color.light_orange)
    val darkOrange = colorResource(R.color.dark_orange)
    val white = colorResource(R.color.white)

    // Importación de textos del archivo strings.xml
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

    // Listado de botones para la orientación apaisada
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

    // Listado de botones para la orientación vertical
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

    // Variable que contiene el listado de botones a generar, dependiendo del valor de isLandscape
    val buttonRows = if (isLandscape) buttonRowsLandscape else buttonRowsPortrait

    /**
     * Maneja el clic de los botones de la calculadora, teniendo en cuenta el texto que contienen.
     *
     * @param text Texto del botón que ha sido presionado.
     */
    fun onBtnClick(text: String) {
        when (text) {
            // Si se presiona el botón AC, se reinician los campos de operación y resultado
            textBtnAc -> {
                textOperation = ""
                textResult = ""
            }
            // Si se presiona el botón de suprimir, se elimina el último caracter del campo de operación
            textBtnDel -> if (textOperation.isNotEmpty()) textOperation = textOperation.dropLast(1)
            // Si se presiona el botón de igualar, se evalua la expresión del campo de operación
            textBtnEquals -> {
                textResult =
                    if (evaluateExpression(changeOperators(textOperation)).isNaN()) textError
                    else evaluateExpression(changeOperators(textOperation)).toString()
            }
            // Si se presiona cualquier otro botón, se añade su texto al campo de operación
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

/**
 * Componente para generar filas con botones.
 *
 * @param buttonRows Listado de botones a añadir en cada fila.
 * @param onBtnClick Función para manejar el clic en los botones.
 */
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

/**
 * Componente para crear un botón con modificaciones de estilo.
 *
 * @param text Texto del botón.
 * @param color Color de fondo del botón.
 * @param modifier Modifier del botón.
 * @param onButtonClick Función onClick del botón.
 */
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

/**
 * Evalúa la expresión mediante la librería exp4j.
 *
 * @param expression Expresión a evaluar.
 * @return Resultado de la expresión en caso de que pueda llevarse a cabo y NaN en caso contrario.
 */
fun evaluateExpression(expression: String): Double {
    return try {
        val expr = ExpressionBuilder(expression).build()
        expr.evaluate()
    } catch (e: Exception) {
        Double.NaN
    }
}

/**
 * Cambia los operadores que se muestran en la calculadora, por operadores que puedan ser interpretados correctamente por exp4j.
 *
 * @param expression Texto que contiene la expresión a ser evaluada y en la cual se realizará el reemplazo de operadores.
 * @return Expresión con los operadores cambiados.
 */
fun changeOperators(expression: String): String {
    var newExpression: String = ""
    newExpression = expression.replace('×', '*').replace('÷', '/')
    return newExpression
}

/**
 * Componente de vista previa para la aplicación de la calculadora.
 *
 */
@Preview(showBackground = true)
@Composable
fun CalculadoraPreview() {
    Interfaz_Calculadora_ComposeTheme {
        Calculadora()
    }
}