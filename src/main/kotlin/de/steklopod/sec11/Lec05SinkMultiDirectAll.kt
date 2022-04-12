package de.steklopod.sec11

import de.steklopod.utils.Util.sleepSeconds
import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Sinks
import java.time.Duration

object Lec05SinkMultiDirectAll {
    @JvmStatic
    fun main(args: Array<String>) {
        System.setProperty("reactor.bufferSize.small", "16")

        // handle through which we would push items
        val sink = Sinks.many()
            .multicast()
            .directBestEffort<Int>()

        // handle through which subscribers will receive items
        sink.asFlux().run {
            subscribe(subscriber("sam"))
            delayElements(Duration.ofMillis(200)).subscribe(subscriber("mike"))
        }

        for (i in 0..99) {
            sink.tryEmitNext(i)
        }

        sleepSeconds(10)
    }
}
