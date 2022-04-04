package de.steklopod.sec01

import reactor.core.publisher.Mono

object Lec02MonoJust {
    @JvmStatic
    fun main(args: Array<String>) {
        // publisher
        val mono = Mono.just(1)
        println(mono)
        mono.subscribe { i: Int -> println("Received : $i") }
    }
}
