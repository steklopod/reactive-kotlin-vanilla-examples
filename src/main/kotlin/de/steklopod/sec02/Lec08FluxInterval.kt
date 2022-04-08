package de.steklopod.sec02

import de.steklopod.utils.Util.onNext
import de.steklopod.utils.Util.sleepSeconds
import reactor.core.publisher.Flux
import java.time.Duration

object Lec08FluxInterval {
    @JvmStatic
    fun main(args: Array<String>) {
        Flux.interval(Duration.ofSeconds(1))
            .subscribe(onNext())
        sleepSeconds(5)
    }
}
