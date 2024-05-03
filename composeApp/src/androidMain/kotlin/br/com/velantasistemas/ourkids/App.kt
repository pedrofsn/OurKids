package br.com.velantasistemas.ourkids

import android.app.Application
import android.content.Context
import br.com.velantasistemas.ourkids.database.Factory
import br.com.velantasistemas.ourkids.features.database.AppContainer

class App : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        context = this
        container = AppContainer(Factory(this))
    }

    companion object {
        lateinit var context: Context
    }
}
