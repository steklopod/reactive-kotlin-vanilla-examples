package de.steklopod.sec02

import reactor.core.publisher.Flux

object Lec02MultipleSubscribers {
    @JvmStatic
    fun main(args: Array<String>) {
        val integerFlux = Flux.just(1, 2, 3, 4)
        val evenFlux = integerFlux.filter { i: Int -> i % 2 == 0 }
        integerFlux
            .subscribe { i: Int -> println("Sub 1 : $i") }
        evenFlux
            .subscribe { i: Int -> println("Sub 2 : $i") }
    }
}
