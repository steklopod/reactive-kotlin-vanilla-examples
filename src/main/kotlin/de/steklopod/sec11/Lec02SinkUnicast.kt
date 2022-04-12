package de.steklopod.sec11

import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Sinks

object Lec02SinkUnicast {
    @JvmStatic
    fun main(args: Array<String>) {
        // handle through which we would push items
        val sink = Sinks.many()
            .unicast()
         // .multicast()                     // for `mike` subscriber
            .onBackpressureBuffer<String>()

        // handle through which subscribers will receive items
        sink.asFlux().run {
            subscribe(subscriber("sam"))
            subscribe(subscriber("mike")) // won't get messages because of `unicast` broadcasting
        }

        sink.run {
            tryEmitNext("👋🏻 hi")
            tryEmitNext("🌚 how are you")
            tryEmitNext("⏱ ???")
        }
    }

}
