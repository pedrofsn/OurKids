package br.com.velantasistemas.ourkids.features.demo

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch

@Composable
fun DoRequestScreen() {
    var text by remember { mutableStateOf("Click to load") }
    val scope = rememberCoroutineScope()
    Button(
        onClick = {
            scope.launch {
                text = try {
                    text = "Loading..."
                    AwesomeDomainMultiplatform().doRequest()
                } catch (e: Exception) {
                    e.toString()
                }
            }
        }
    ) {
        Text(text)
    }
}
