package br.com.velantasistemas.ourkids.features.database

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

const val dbFileName = "ourkids.db"

@Database(entities = [TodoEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDao(): TodoDao
}

@Dao
interface TodoDao {
    @Insert
    suspend fun insert(item: TodoEntity): Long

    @Query("SELECT count(*) FROM TodoEntity")
    suspend fun count(): Int

    @Query("SELECT * FROM TodoEntity ORDER BY id DESC")
    fun getAllAsFlow(): Flow<List<TodoEntity>>
}

@Entity
data class TodoEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val content: String
)
