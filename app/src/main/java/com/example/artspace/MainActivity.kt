package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.artspace.ui.theme.ArtSpaceTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                ArtworkAppWithClass()
            }
        }
    }
}


// Clase que representa los datos de una pantalla (imagen, título y subtítulo)
open class ScreenContent(
    val imageRes: Int,
    val title: String,
    val subtitle: String
)


@Composable
fun ArtworkAppWithClass() {
    // Estado de la pantalla actual
    var currentScreen by remember { mutableStateOf(0) }
    val borderColor = Color(13,124,102)
    val backgroundColor = Color(126,172,181).copy(alpha = 0.5f)
    // Crear las pantallas utilizando la clase ScreenContent
    val screens = listOf(
        ScreenContent(
            R.drawable.primavera,
            stringResource(R.string.danza_de_colores),
            "DALLE (12-09-2024)"
        ),
        ScreenContent(
            R.drawable.verano,
            stringResource(R.string.verano_eterno),
            "Copilot (12-09-2024)"
        ),
        ScreenContent(
            R.drawable.otono,
            stringResource(R.string.sueno_otonal),
            "DALLE (12-09-2024)"
        ),
        ScreenContent(
            R.drawable.invierno,
            stringResource(R.string.abrazando_el_frio),
            "Copilot (12-09-2024)"
        )
    )

    // Obtener la pantalla actual
    val screen = screens[currentScreen]

    // Crear la interfaz
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Imagen dinámica
        Image(
            painter = painterResource(id = screen.imageRes),
            contentDescription = null,
            modifier = Modifier
                .height(300.dp)
                .width(350.dp)
                //.fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .border(4.dp, borderColor, RoundedCornerShape(20.dp)),
            contentScale = ContentScale.Crop
        )

        // Título dinámico
        Text(
            text = screen.title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = borderColor,
            modifier = Modifier.padding(top = 16.dp)
        )

        // Subtítulo dinámico
        Text(
            text = screen.subtitle,
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 8.dp)
        )

        // Espaciado flexible
        Spacer(modifier = Modifier.height(32.dp))

        // Botones "Anterior" y "Siguiente"
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp)
        ) {
            Button(onClick = {
                currentScreen = if (currentScreen > 0) {
                    currentScreen - 1
                } else {
                    screens.size - 1
                }
            }) {
                Text("Anterior")
            }

            Button(onClick = {
                currentScreen = if (currentScreen < screens.size - 1) {
                    currentScreen + 1
                } else {
                    0
                }
            }) {
                Text("Siguiente")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtworkAppWithClass()
    }
}