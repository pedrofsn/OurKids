package br.com.velantasistemas.ourkids.features.database

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val focusManager = LocalFocusManager.current
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberLazyListState()
    val database = getMyDatabase().db
    val db = getRoomDatabase(database)

    var location by remember { mutableStateOf("") }
    val state by db.getDao().getAllAsFlow().collectAsState(emptyList())

    Column(modifier = Modifier.fillMaxWidth()) {
        TextField(
            label = { Text("Digite algo") },
            modifier = Modifier.fillMaxWidth(),
            value = location,
            onValueChange = { location = it },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    null,
                    modifier = Modifier.clickable {
                        location = ""
                    }
                )
            }
        )
        Button(
            modifier = Modifier.align(Alignment.End),
            onClick = {
                focusManager.clearFocus()

                val item = TodoEntity(title = todayDate(), content = location)
                coroutineScope.launch {
                    if(db.getDao().insert(item) > 0) {
                        scrollState.animateScrollToItem(0)
                    }
                }

                location = ""
            },
        ) {
            Text("Clique para salvar")
        }
        Spacer(modifier = Modifier.weight(1f))

        LazyColumn(state = scrollState, modifier = Modifier.fillMaxSize()) {
            items(items = state, key = { it.id }) { item ->
                TodoItem(item)
            }
        }
    }
}

@Composable
fun TodoItem(item: TodoEntity) {
    Card(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = item.title, color = Color.Gray, fontSize = 21.sp)
            Spacer(Modifier.padding(8.dp))
            Text(text = item.content)
        }
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
