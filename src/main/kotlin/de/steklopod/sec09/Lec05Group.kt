package de.steklopod.sec09

import de.steklopod.utils.Util.sleepSeconds
import reactor.core.publisher.Flux
import reactor.core.publisher.GroupedFlux
import java.time.Duration

object Lec05Group {
    @JvmStatic
    fun main(args: Array<String>) {
        Flux.range(1, 30)
            .delayElements(Duration.ofSeconds(1))
            .groupBy { i: Int -> i % 2 } // key 0, 1
            .subscribe { gf: GroupedFlux<Int, Int> -> process(gf, gf.key()) }
        sleepSeconds(60)
    }

    private fun process(flux: Flux<Int>, key: Int) {
        println("Called")
        flux.subscribe { i: Int -> println("Key : $key, Item : $i") }
    }
}
