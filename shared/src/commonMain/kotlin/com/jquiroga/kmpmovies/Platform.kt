package com.jquiroga.kmpmovies

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform