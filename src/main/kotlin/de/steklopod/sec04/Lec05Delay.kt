package de.steklopod.sec04

import de.steklopod.utils.Util.sleepSeconds
import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Flux
import java.time.Duration

object Lec05Delay {
    @JvmStatic
    fun main(args: Array<String>) {
        System.setProperty("reactor.bufferSize.x", "9")
        Flux.range(1, 100) // 32
            .log()
            .delayElements(Duration.ofSeconds(1))
            .subscribe(subscriber())
        sleepSeconds(60)
    }
}
