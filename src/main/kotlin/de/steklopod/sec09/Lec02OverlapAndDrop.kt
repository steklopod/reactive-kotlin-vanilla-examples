package de.steklopod.sec09

import de.steklopod.utils.Util.sleepSeconds
import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Flux
import java.time.Duration

object Lec02OverlapAndDrop {
    @JvmStatic
    fun main(args: Array<String>) {
        eventStream()
            .buffer(3, 5)
            .subscribe(subscriber())

        sleepSeconds(60)
    }

    private fun eventStream(): Flux<String> = Flux.interval(Duration.ofMillis(300))
        .map { i: Long -> "event$i" }
}
