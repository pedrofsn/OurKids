package br.com.velantasistemas.ourkids.database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import br.com.velantasistemas.ourkids.features.database.AppDatabase
import br.com.velantasistemas.ourkids.features.database.dbFileName
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

actual class Factory {
    actual fun createRoomDatabase(): AppDatabase {
        val dbFile = "${fileDirectory()}/$dbFileName"
        return Room.databaseBuilder<AppDatabase>(
            name = dbFile,
            factory =  { AppDatabase::class.instantiateImpl() }
        ).setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }

//    actual fun createCartDataStore(): CartDataStore {
//        return CartDataStore {
//            "${fileDirectory()}/cart.json"
//        }
//    }

    @OptIn(ExperimentalForeignApi::class)
    private fun fileDirectory(): String {
        val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        return requireNotNull(documentDirectory).path!!
    }

//    actual fun createApi(): FruittieApi = commonCreateApi()
}
