package de.steklopod.sec04

import de.steklopod.utils.Util.subscriber
import org.reactivestreams.Subscription
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink
import reactor.core.publisher.SignalType

object Lec03DoCallbacks {
    @JvmStatic
    fun main(args: Array<String>) {
        Flux.create { fluxSink: FluxSink<Any> ->
            println("inside create")
            for (i in 0..4) {
                fluxSink.next(i)
            }
            // fluxSink.complete();
            fluxSink.error(RuntimeException("oops"))
            println("--completed")
        }
            .doOnComplete { println("doOnComplete") }
            .doFirst { println("doFirst") }
            .doOnNext { o: Any -> println("doOnNext : $o") }
            .doOnSubscribe { s: Subscription -> println("doOnSubscribe$s") }
            .doOnRequest { l: Long -> println("doOnRequest : $l") }
            .doOnError { err: Throwable -> println("doOnError : " + err.message) }
            .doOnTerminate { println("doOnTerminate") }
            .doOnCancel { println("doOnCancel") }
            .doFinally { signal: SignalType -> println("doFinally 1 : $signal") }
            .doOnDiscard(Any::class.java) { o: Any -> println("doOnDiscard : $o") }
            .take(2)
            .doFinally { signal: SignalType -> println("doFinally 2 : $signal") }
            .subscribe(subscriber())
    }
}
