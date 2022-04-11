package de.steklopod.sec10

import de.steklopod.utils.Util.faker
import de.steklopod.utils.Util.sleepSeconds
import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.SynchronousSink
import reactor.util.retry.Retry
import reactor.util.retry.Retry.RetrySignal
import java.time.Duration

object Lec04RetryWhenAdvanced {
    @JvmStatic
    fun main(args: Array<String>) {
        orderService(faker().business().creditCardNumber())
            .retryWhen(Retry.from { flux: Flux<RetrySignal> ->
                flux
                    .doOnNext { rs: RetrySignal ->
                        println(rs.totalRetries())
                        println(rs.failure())
                    }
                    .handle { rs: RetrySignal, synchronousSink: SynchronousSink<Any?> ->
                        if (rs.failure().message == "500") synchronousSink.next(
                            1
                        ) else synchronousSink.error(rs.failure())
                    }
                    .delayElements(Duration.ofSeconds(1))
            })
            .subscribe(subscriber())
        sleepSeconds(60)
    }

    // order service
    private fun orderService(ccNumber: String): Mono<String> = Mono.fromSupplier {
        processPayment(ccNumber)
        faker().idNumber().valid()
    }

    // payment service
    private fun processPayment(ccNumber: String) {
        val random = faker().random().nextInt(1, 10)
        if (random < 8) throw RuntimeException("500")
        else if (random < 10) throw RuntimeException("404")
    }
}
