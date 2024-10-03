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
import androidx.compose.material3.HorizontalDivider
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
    val esApaisado = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    CalculadoraLayout(esApaisado)
}

/**
 * Representa el diseño de la interfaz que se adapta a la orientación del dispositivo.
 *
 * @param esApaisado Un booleano que indica si la orientación de la pantalla es horizontal (true) o vertical (false).
 */
@Composable
fun CalculadoraLayout(
    esApaisado: Boolean
) {
    // Variable para controlar el texto de operación y guardar su estado
    var textOperacion by rememberSaveable { mutableStateOf("") }
    // Variable para controlar el texto del resultado y guardar su estado
    var textResultado by rememberSaveable { mutableStateOf("") }

    // Imortación de colores del archivo colors.xml
    val azulOscuro = colorResource(R.color.dark_blue)
    val azulClaro = colorResource(R.color.light_blue)
    val azulMedio = colorResource(R.color.medium_blue)
    val naranjaClaro = colorResource(R.color.light_orange)
    val naranjaOscuro = colorResource(R.color.dark_orange)
    val blanco = colorResource(R.color.white)

    // Importación de textos del archivo strings.xml
    val textoBtn0 = stringResource(R.string.btn0)
    val textoBtn1 = stringResource(R.string.btn1)
    val textoBtn2 = stringResource(R.string.btn2)
    val textoBtn3 = stringResource(R.string.btn3)
    val textoBtn4 = stringResource(R.string.btn4)
    val textoBtn5 = stringResource(R.string.btn5)
    val textoBtn6 = stringResource(R.string.btn6)
    val textoBtn7 = stringResource(R.string.btn7)
    val textoBtn8 = stringResource(R.string.btn8)
    val textoBtn9 = stringResource(R.string.btn9)
    val textoBtnSumar = stringResource(R.string.btn_sumar)
    val textoBtnRestar = stringResource(R.string.btn_restar)
    val textoBtnMultiplicar = stringResource(R.string.btn_multiplicar)
    val textoBtnDividir = stringResource(R.string.btn_dividir)
    val textoBtnAbrirParentesis = stringResource(R.string.btn_abrir_parentesis)
    val textoBtnCerrarParentesis = stringResource(R.string.btn_cerrar_parentesis)
    val textoBtnAc = stringResource(R.string.btn_ac)
    val textoBtnComa = stringResource(R.string.btn_coma)
    val textoBtnIgualar = stringResource(R.string.btn_igualar)
    val textoBtnSuprimir = stringResource(R.string.btn_suprimir)
    val textoError = stringResource(R.string.texto_error)

    // Listado de botones para la orientación apaisada
    val filasBotonesApaisado = listOf(
        listOf(
            Triple(textoBtn7, azulMedio, 1f),
            Triple(textoBtn8, azulMedio, 1f),
            Triple(textoBtn9, azulMedio, 1f),
            Triple(textoBtnSuprimir, naranjaClaro, 1f),
            Triple(textoBtnAc, naranjaClaro, 2.05f)
        ), listOf(
            Triple(textoBtn4, azulMedio, 1f),
            Triple(textoBtn5, azulMedio, 1f),
            Triple(textoBtn6, azulMedio, 1f),
            Triple(textoBtnDividir, naranjaOscuro, 1f),
            Triple(textoBtnMultiplicar, naranjaOscuro, 1f),
            Triple(textoBtnAbrirParentesis, azulClaro, 1f)
        ), listOf(
            Triple(textoBtn1, azulMedio, 1f),
            Triple(textoBtn2, azulMedio, 1f),
            Triple(textoBtn3, azulMedio, 1f),
            Triple(textoBtnRestar, naranjaOscuro, 1f),
            Triple(textoBtnSumar, naranjaOscuro, 1f),
            Triple(textoBtnCerrarParentesis, azulClaro, 1f)
        ), listOf(
            Triple(textoBtn0, azulMedio, 1f),
            Triple(textoBtnComa, azulClaro, 1f),
            Triple(textoBtnIgualar, naranjaClaro, 1f)
        )
    );

    // Listado de botones para la orientación vertical
    val filasBotonesVertical = listOf(
        listOf(
            Triple(textoBtnAc, naranjaClaro, 3.05f),
            Triple(textoBtnSuprimir, naranjaClaro, 1f)
        ), listOf(
            Triple(textoBtnAbrirParentesis, azulClaro, 1f),
            Triple(textoBtnCerrarParentesis, azulClaro, 1f),
            Triple(textoBtnComa, azulClaro, 1f),
            Triple(textoBtnDividir, naranjaOscuro, 1f)
        ), listOf(
            Triple(textoBtn7, azulMedio, 1f),
            Triple(textoBtn8, azulMedio, 1f),
            Triple(textoBtn9, azulMedio, 1f),
            Triple(textoBtnMultiplicar, naranjaOscuro, 1f)
        ), listOf(
            Triple(textoBtn4, azulMedio, 1f),
            Triple(textoBtn5, azulMedio, 1f),
            Triple(textoBtn6, azulMedio, 1f),
            Triple(textoBtnRestar, naranjaOscuro, 1f)
        ), listOf(
            Triple(textoBtn1, azulMedio, 1f),
            Triple(textoBtn2, azulMedio, 1f),
            Triple(textoBtn3, azulMedio, 1f),
            Triple(textoBtnSumar, naranjaOscuro, 1f)
        ), listOf(
            Triple(textoBtn0, azulMedio, 1f),
            Triple(textoBtnIgualar, naranjaClaro, 1f)
        )
    );

    // Variable que contiene el listado de botones a generar, dependiendo del valor de isLandscape
    val filasBotonesActual = if (esApaisado) filasBotonesApaisado else filasBotonesVertical

    /**
     * Maneja el clic de los botones de la calculadora, teniendo en cuenta el texto que contienen.
     *
     * @param texto Texto del botón que ha sido presionado.
     */
    fun funcionalidadBoton(texto: String) {
        when (texto) {
            // Si se presiona el botón AC, se reinician los campos de operación y resultado
            textoBtnAc -> {
                textOperacion = ""
                textResultado = ""
            }
            // Si se presiona el botón de suprimir, se elimina el último caracter del campo de operación
            textoBtnSuprimir -> if (textOperacion.isNotEmpty()) textOperacion = textOperacion.dropLast(1)
            // Si se presiona el botón de igualar, se evalua la expresión del campo de operación
            textoBtnIgualar -> {
                textResultado =
                    if (evaluarOperacion(cambiarOperadores(textOperacion))
                            .isNaN()) textoError
                    else evaluarOperacion(cambiarOperadores(textOperacion))
                        .toString()
            }
            // Si se presiona cualquier otro botón, se añade su texto al campo de operación
            else -> textOperacion += texto
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(azulOscuro)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(if (esApaisado) 60.dp else 20.dp, 10.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp)
                    .weight(if (esApaisado) 0.75f else 1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = textOperacion, color = blanco, style = TextStyle(fontSize = 40.sp))
            }

            HorizontalDivider(
                color = Color.White,
                thickness = 2.dp
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 5.dp)
                    .weight(if (esApaisado) 0.5f else 0.75f),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(text = textResultado, color = blanco, style = TextStyle(fontSize = 30.sp))
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier
                    .weight(if (esApaisado) 3f else 4f)
                    .padding(0.dp, 10.dp)
            ) {
                FilasConBotones(filasBotonesActual, ::funcionalidadBoton, esApaisado)
            }
        }
    }
}

/**
 * Componente para generar filas con botones.
 *
 * @param filasBotones Listado de botones a añadir en cada fila.
 * @param funcionalidadBoton Función para manejar el clic en los botones.
 * @param esApaisado Un booleano que indica si la orientación de la pantalla es horizontal (true) o vertical (false).
 */
@Composable
fun FilasConBotones(
    filasBotones: List<List<Triple<String, Color, Float>>>,
    funcionalidadBoton: (String) -> Unit,
    esApaisado: Boolean
) {
    for (row in filasBotones) {
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            for ((buttonText, buttonColor, buttonWeight) in row) {
                BotonPersonalizado(
                    texto = buttonText,
                    color = buttonColor,
                    modifier = Modifier.weight(buttonWeight),
                    funcionalidadBoton = funcionalidadBoton,
                    esApaisado = esApaisado
                )
            }
        }
    }
}

/**
 * Componente para crear un botón con modificaciones de estilo.
 *
 * @param texto Texto del botón.
 * @param color Color de fondo del botón.
 * @param modifier Modifier del botón.
 * @param funcionalidadBoton Función onClick del botón.
 * @param esApaisado Un booleano que indica si la orientación de la pantalla es horizontal (true) o vertical (false).
 */
@Composable
fun BotonPersonalizado(
    texto: String,
    color: Color,
    modifier: Modifier = Modifier,
    funcionalidadBoton: (String) -> Unit,
    esApaisado: Boolean
) {
    val paddingBoton: Float = if (esApaisado) 2.5f else 15f

    Button(
        onClick = { funcionalidadBoton(texto) },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = color),
        shape = RoundedCornerShape(5.dp)
    ) {
        Text(
            texto,
            style = TextStyle(fontSize = if (texto == stringResource(R.string.btn_suprimir)) 27.sp else 30.sp),
            modifier = Modifier
                .padding(0.dp, paddingBoton.dp)
                .fillMaxWidth()
                .align(Alignment.CenterVertically),
            textAlign = TextAlign.Center
        )

    }
}

/**
 * Evalúa la expresión mediante la librería exp4j.
 *
 * @param expresion Expresión a evaluar.
 * @return Resultado de la expresión en caso de que pueda llevarse a cabo y NaN en caso contrario.
 */
fun evaluarOperacion(expresion: String): Double {
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
 * @param expresion Texto que contiene la expresión a ser evaluada y en la cual se realizará el reemplazo de operadores.
 * @return Expresión con los operadores cambiados.
 */
fun cambiarOperadores(expresion: String): String {
    val expresionValida = expresion.replace('×', '*')
                                .replace('÷', '/')
                                .replace(',', '.')
    return expresionValida
}

/**
 * Componente de vista previa para la aplicación de la calculadora.
 *
 */
// Apaisado
@Preview(
    showBackground = true,
    heightDp = 412,
    widthDp = 873
)
// Vertical
@Preview(
    showBackground = true,
    widthDp = 412,
    heightDp = 873
)
@Composable
fun CalculadoraPreview() {
    Interfaz_Calculadora_ComposeTheme {
        Calculadora()
    }
}