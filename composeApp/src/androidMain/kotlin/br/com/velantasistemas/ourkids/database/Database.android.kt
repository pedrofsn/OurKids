package br.com.velantasistemas.ourkids.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.velantasistemas.ourkids.App
import br.com.velantasistemas.ourkids.features.database.AppDatabase

private fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<AppDatabase> {
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath("my_room.db")
    return Room.databaseBuilder<AppDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}


class MyDatabaseImpl(ctx: Context) : MyDatabase {
    override val db: RoomDatabase.Builder<AppDatabase> = getDatabaseBuilder(ctx)
}

actual fun getMyDatabase(): MyDatabase = MyDatabaseImpl(App.context)
