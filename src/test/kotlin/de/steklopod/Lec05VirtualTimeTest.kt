package de.steklopod

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier
import java.time.Duration

class Lec05VirtualTimeTest {
    @Test
    fun test1() {
        StepVerifier.withVirtualTime { timeConsumingFlux() }
            .thenAwait(Duration.ofSeconds(30))
            .expectNext("1a", "2a", "3a", "4a")
            .verifyComplete()
    }

    @Test
    fun test2() {
        StepVerifier.withVirtualTime { timeConsumingFlux() }
            .expectSubscription() // sub is an event
            .expectNoEvent(Duration.ofSeconds(4))
            .thenAwait(Duration.ofSeconds(20))
            .expectNext("1a", "2a", "3a", "4a")
            .verifyComplete()
    }

    private fun timeConsumingFlux(): Flux<String?> {
        return Flux.range(1, 4)
            .delayElements(Duration.ofSeconds(5))
            .map { i: Int -> i.toString() + "a" }
    }
}
