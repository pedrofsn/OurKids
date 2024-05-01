package br.com.velantasistemas.ourkids.settings

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
