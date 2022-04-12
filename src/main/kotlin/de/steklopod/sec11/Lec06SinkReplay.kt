package de.steklopod.sec11

import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Sinks

object Lec06SinkReplay {
    @JvmStatic
    fun main(args: Array<String>) {
        // handle through which we would push items
        val sink = Sinks.many()
            .replay()
            .all<String>()

        // handle through which subscribers will receive items
        val flux = sink.asFlux()

        sink.tryEmitNext("👋🏻 hi")
        sink.tryEmitNext("🌚 how are you")

        flux.subscribe(subscriber("sam"))
        flux.subscribe(subscriber("mike"))

        sink.tryEmitNext("🪤???")

        flux.subscribe(subscriber("jake"))

        sink.tryEmitNext("📥 new msg")
    }
}
