package de.steklopod.sec11

import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Sinks

object Lec01SinkOne {
    @JvmStatic
    fun main(args: Array<String>) {
        // mono 1 value / empty / error
        val sink = Sinks.one<Any>()
        val mono = sink.asMono()

        mono.subscribe(subscriber("sam"))
        mono.subscribe(subscriber("mike"))
/*
        sink.emitValue("🐸") { signalType, emitResult ->
            println("🐸signalType: " + signalType.name)
            println("🐸emitResult: " + emitResult.name)
            false
        };

        sink.emitValue("🤖🤖") { signalType, emitResult ->
            println("🤖🤖signalType: " + signalType.name)
            println("🤖🤖emitResult: " + emitResult.name)
            false
        }*/

        sink.tryEmitValue("👋🏻👋🏻")
    }
}
