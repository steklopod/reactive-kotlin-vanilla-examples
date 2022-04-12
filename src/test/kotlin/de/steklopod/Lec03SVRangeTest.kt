package de.steklopod

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier

class Lec03SVRangeTest {
    @Test
    fun test1() {
        val range = Flux.range(1, 50)
        StepVerifier.create(range)
            .expectNextCount(50)
            .verifyComplete()
    }

    @Test
    fun test2() {
        val range = Flux.range(1, 50)
        StepVerifier.create(range)
            .thenConsumeWhile { i: Int -> i < 100 }
            .verifyComplete()
    }
}
