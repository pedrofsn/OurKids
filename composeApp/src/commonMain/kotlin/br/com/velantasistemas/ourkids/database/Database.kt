package br.com.velantasistemas.ourkids.database

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import br.com.velantasistemas.ourkids.features.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO


interface MyDatabase {
    val db: RoomDatabase.Builder<AppDatabase>
}

expect fun getMyDatabase(): MyDatabase

fun getRoomDatabase(
    builder: RoomDatabase.Builder<AppDatabase>
): AppDatabase {
    return builder
//        .addMigrations(MIGRATIONS)
//        .fallbackToDestructiveMigrationOnDowngrade()
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}
