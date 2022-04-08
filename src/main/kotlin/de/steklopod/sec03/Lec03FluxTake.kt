package de.steklopod.sec03

import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Flux

object Lec03FluxTake {
    @JvmStatic
    fun main(args: Array<String>) {

        // map
        // filter
        Flux.range(1, 10)
            .log()
            .take(3) // cancels
            .log()
            .subscribe(subscriber())
    }
}
