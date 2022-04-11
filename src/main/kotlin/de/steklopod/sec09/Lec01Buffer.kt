package de.steklopod.sec09

import de.steklopod.utils.Util.sleepSeconds
import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Flux
import java.time.Duration

object Lec01Buffer {
    @JvmStatic
    fun main(args: Array<String>) {
        eventStream()
            .bufferTimeout(5, Duration.ofSeconds(2))
            .subscribe(subscriber())

        sleepSeconds(60)
    }

    private fun eventStream(): Flux<String> = Flux.interval(Duration.ofMillis(800))
        .map { i: Long -> "event$i" }
}
