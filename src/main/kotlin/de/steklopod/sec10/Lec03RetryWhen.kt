package de.steklopod.sec10

import de.steklopod.utils.Util.faker
import de.steklopod.utils.Util.sleepSeconds
import de.steklopod.utils.Util.subscriber
import org.reactivestreams.Subscription
import reactor.core.publisher.Flux
import reactor.util.retry.Retry
import java.time.Duration

object Lec03RetryWhen {
    @JvmStatic
    fun main(args: Array<String>) {
        integers
            .retryWhen(Retry.fixedDelay(2, Duration.ofSeconds(3)))
            .subscribe(subscriber())
        sleepSeconds(60)
    }

    private val integers: Flux<Int>
        get() = Flux.range(1, 3)
            .doOnSubscribe { s: Subscription? -> println("Subscribed") }
            .doOnComplete { println("--Completed") }
            .map { i: Int -> i / if (faker().random().nextInt(1, 5) > 3) 0 else 1 }
            .doOnError { err: Throwable? -> println("--error : ${err?.message}") }
}
