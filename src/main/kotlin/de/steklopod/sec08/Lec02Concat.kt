package de.steklopod.sec08

import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Flux

object Lec02Concat {
    @JvmStatic
    fun main(args: Array<String>) {
        val flux1 = Flux.just("a", "b")
        val flux2 = Flux.error<String>(RuntimeException("oops"))
        val flux3 = Flux.just("c", "d", "e")
        val flux = Flux.concatDelayError(flux1, flux2, flux3)
        flux.subscribe(subscriber())
    }
}
