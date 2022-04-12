package de.steklopod.sec13

import de.steklopod.utils.Util.onNext
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

object Lec01Checkpoint {
    @JvmStatic
    fun main(args: Array<String>) {
        Flux.range(1, 10)
            .checkpoint("cp1")
            .map { it + it }
            .checkpoint("cp2")
            .map { it / 2 }
            .checkpoint("cp3")
            .flatMap { Mono.just(if (it < 3) it else it / (5 - it)) }
            .checkpoint("cp4")
            .map { it + 2 }
            .checkpoint("cp5")
            .filter { it % 2 == 0 }
            .checkpoint("cp6")
            .subscribe(onNext())
    }
}
