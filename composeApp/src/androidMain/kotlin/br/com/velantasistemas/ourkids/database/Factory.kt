package br.com.velantasistemas.ourkids.database

import android.app.Application
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import br.com.velantasistemas.ourkids.features.database.AppDatabase
import br.com.velantasistemas.ourkids.features.database.dbFileName
import kotlinx.coroutines.Dispatchers

actual class Factory(private val app: Application) {
    actual fun createRoomDatabase(): AppDatabase {
        val dbFile = app.getDatabasePath(dbFileName)
        return Room.databaseBuilder<AppDatabase>(app, dbFile.absolutePath)
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }

//    actual fun createCartDataStore(): CartDataStore {
//        return CartDataStore {
//            app.filesDir.resolve(
//                "cart.json",
//            ).absolutePath
//        }
//    }

//    actual fun createApi(): FruittieApi = commonCreateApi()
}
