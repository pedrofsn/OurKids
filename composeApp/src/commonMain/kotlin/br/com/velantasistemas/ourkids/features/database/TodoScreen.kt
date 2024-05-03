package br.com.velantasistemas.ourkids.features.database

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import br.com.velantasistemas.ourkids.database.Factory
import br.com.velantasistemas.ourkids.database.getMyDatabase
import br.com.velantasistemas.ourkids.database.getRoomDatabase
import br.com.velantasistemas.ourkids.features.input.todayDate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

@Composable
fun TodoScreen() {
    val coroutineScope = rememberCoroutineScope()
    val database = getMyDatabase().db
    val db = getRoomDatabase(database)

    var location by remember { mutableStateOf("Digite qualquer coisa") }
    val state by db.getDao().getAllAsFlow().collectAsState(emptyList())

    Column(modifier = Modifier.fillMaxSize()) {
        TextField(value = location, onValueChange = { location = it })
        Button(
            onClick = {
                val item = TodoEntity(title = todayDate(), content = location)
                coroutineScope.launch {
                    db.getDao().insert(item)
                }
            }
        ) {
            Text("Clique para salvar")
        }
        Spacer(modifier = Modifier.weight(1f))

        LazyColumn {
            items(items = state, key = { it.id }) { item ->
                TodoItem(item)
            }
        }
    }
}

@Composable
fun TodoItem(item: TodoEntity) {
    Column {
        Text(text = item.title)
        Text(text = item.content)
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
