package de.steklopod

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier

class Lec01SVDemoTest {
    @Test
    fun test1() {
        val just = Flux.just(1, 2, 3)
        StepVerifier.create(just)
            .expectNext(1)
            .expectNext(2)
            .expectNext(3)
            .verifyComplete()
    }

    @Test
    fun test2() {
        val just = Flux.just(1, 2, 3)
        StepVerifier.create(just)
            .expectNext(1, 2, 3)
            .verifyComplete()
    }
}
