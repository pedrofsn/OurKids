package br.com.velantasistemas.ourkids

import android.app.Application
import br.com.velantasistemas.ourkids.database.Factory
import br.com.velantasistemas.ourkids.features.database.AppContainer

class App : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(Factory(this))
    }
}
