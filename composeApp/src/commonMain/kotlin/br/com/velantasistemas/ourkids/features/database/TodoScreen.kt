package br.com.velantasistemas.ourkids.features.database

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import br.com.velantasistemas.ourkids.database.Factory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

@Composable
fun TodoScreen() {
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        Button(
            onClick = {
                val item = TodoEntity(title = "Teste", content = "Conteúdo")
                coroutineScope.launch {
//                    Factory.createRoomDatabase().getDao().insert(item)
                }
            }
        ) {
            Text("Clique para salvar")
        }
        Spacer(modifier = Modifier.weight(1f))

    }
}


class DataRepository(
    private var database: AppDatabase,
    private val scope: CoroutineScope,
) {
    fun loadData() = database.getDao().getAllAsFlow()
}


class AppContainer(
    private val factory: Factory,
) {
    val dataRepository: DataRepository by lazy {
        DataRepository(
            database = factory.createRoomDatabase(),
            scope = CoroutineScope(Dispatchers.Default + SupervisorJob()),
        )
    }
}
