package br.com.velantasistemas.ourkids.database

import br.com.velantasistemas.ourkids.features.database.AppDatabase

expect class Factory {
    fun createRoomDatabase(): AppDatabase
//    fun createApi(): FruittieApi
//    fun createCartDataStore(): CartDataStore
}
