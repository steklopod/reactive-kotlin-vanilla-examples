package de.steklopod

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier
import reactor.test.StepVerifierOptions

@Disabled
class Lec06ScenarioNameTest {
    @Test
    fun test1() {
        val flux = Flux.just("a", "b", "c")
        val scenarioName = StepVerifierOptions.create().scenarioName("alphabets-test")
        StepVerifier.create(flux, scenarioName)
            .expectNextCount(12)
            .verifyComplete()
    }

    @Test
    fun test2() {
        val flux = Flux.just("a", "b1", "c")
        StepVerifier.create(flux)
            .expectNext("a")
            .`as`("a-test")
            .expectNext("b")
            .`as`("b-test")
            .expectNext("c")
            .`as`("c-test")
            .verifyComplete()
    }
}
