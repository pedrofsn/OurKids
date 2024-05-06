package br.com.velantasistemas.ourkids.database

import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.velantasistemas.ourkids.features.database.AppDatabase
import br.com.velantasistemas.ourkids.features.database.instantiateImpl
import platform.Foundation.NSHomeDirectory

private fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val dbFilePath = NSHomeDirectory() + "/my_room.db"
    return Room.databaseBuilder<AppDatabase>(
        name = dbFilePath,
        factory =  { AppDatabase::class.instantiateImpl() }
    )
}

class MyDatabaseImpl : MyDatabase {
    override val db: RoomDatabase.Builder<AppDatabase> = getDatabaseBuilder()
}

actual fun getMyDatabase(): MyDatabase = MyDatabaseImpl()
