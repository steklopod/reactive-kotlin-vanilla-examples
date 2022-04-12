package de.steklopod

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier

class Lec02SVErrorTest {
    @Test
    fun test1() {
        val just = Flux.just(1, 2, 3)
        val error = Flux.error<Int>(RuntimeException("oops"))
        val concat = Flux.concat(just, error)
        StepVerifier.create(concat)
            .expectNext(1, 2, 3)
            .verifyError()
    }

    @Test
    fun test2() {
        val just = Flux.just(1, 2, 3)
        val error = Flux.error<Int>(RuntimeException("oops"))
        val concat = Flux.concat(just, error)
        StepVerifier.create(concat)
            .expectNext(1, 2, 3)
            .verifyError(RuntimeException::class.java)
    }

    @Test
    fun test3() {
        val just = Flux.just(1, 2, 3)
        val error = Flux.error<Int>(RuntimeException("oops"))
        val concat = Flux.concat(just, error)
        StepVerifier.create(concat)
            .expectNext(1, 2, 3)
            .verifyErrorMessage("oops")
    }
}
