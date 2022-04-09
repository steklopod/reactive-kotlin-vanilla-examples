package de.steklopod.sec06

import de.steklopod.utils.Util.sleepSeconds
import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Flux
import java.time.Duration

object Lec07FluxInterval {
    @JvmStatic
    fun main(args: Array<String>) {
        Flux.range(1, 10)
            .delayElements(Duration.ofSeconds(1))
            .log()
            .subscribe(subscriber())

        sleepSeconds(60)
    }
}
