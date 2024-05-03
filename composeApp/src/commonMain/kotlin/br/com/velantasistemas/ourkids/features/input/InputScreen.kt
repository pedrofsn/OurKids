package br.com.velantasistemas.ourkids.features.input

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun InputScreen() {
    MaterialTheme {
        var location by remember { mutableStateOf("Digite qualquer coisa") }
        var timeAtLocation by remember { mutableStateOf("Clique no botão") }

        Scaffold(
            topBar = {
                TopAppBar(title = { Text("Testando input") })
            },
            content = { _ ->
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(timeAtLocation)
                    Text(location)
                    TextField(value = location, onValueChange = { location = it })
                    Button(onClick = { timeAtLocation = todayDate() }) {
                        Text("Update date")
                    }
                }
            }
        )
    }
}

fun todayDate(): String {
    fun LocalDateTime.format() = toString().substringBefore('T')

    val now = Clock.System.now()
    val zone = TimeZone.currentSystemDefault()
    val formatted = now.toLocalDateTime(zone).format()
    val localDate = LocalDate.parse(formatted)
    val exploded = localDate.toString().split('-')
    return "${exploded[2]}/${exploded[1]}/${exploded[0]}"
}
