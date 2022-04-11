package de.steklopod.sec10

import de.steklopod.utils.Util.faker
import de.steklopod.utils.Util.subscriber
import org.reactivestreams.Subscription
import reactor.core.publisher.Flux
import java.util.concurrent.atomic.AtomicInteger

object Lec02Retry {
    private val atomicInteger = AtomicInteger(1)

    @JvmStatic
    fun main(args: Array<String>) {
        integers
            .retry(2)
            .subscribe(subscriber())
    }

    private val integers: Flux<Int>
        get() = Flux.range(1, 3)
            .doOnSubscribe { println("Subscribed") }
            .doOnComplete { println("--Completed") }
            .map { i: Int -> i / if (faker().random().nextInt(1, 5) > 3) 0 else 1 }
            .doOnError { err: Throwable? -> println("--error : ${err?.message}") }
}
