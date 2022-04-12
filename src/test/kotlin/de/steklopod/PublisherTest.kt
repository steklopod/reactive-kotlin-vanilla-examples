package de.steklopod

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier
import reactor.test.publisher.TestPublisher

@Disabled
class PublisherTest {
    // test publisher - pipeline test
    @Test
    fun test1() {
        val testPublisher = TestPublisher.create<Int>()
        val flux = testPublisher.flux()
        process(flux)

        StepVerifier.create(flux)
            .then { testPublisher.assertWasSubscribed() }
            .then { testPublisher.emit(0, 1, 2) }
            .expectNextCount(4) //  .then(() -> testPublisher.assertWasCancelled())
            //  .expectNext(1, 4, 9)
            .verifyComplete()
    }

    @Test
    fun test2() {
        val testPublisher = TestPublisher.create<Int>()
        StepVerifier.create(testPublisher.flux())
            .then { testPublisher.next(0, 1, 2) }
            .expectNext(1, 4, 9)
            .`as`("first-round")
            .then { testPublisher.emit(0, 1, 3) }
            .expectNext(1, 4, 9)
            .`as`("second-round")
            .expectComplete()
            .verify()
    }

    @Test
    fun test3() {
        val testPublisher = TestPublisher.create<Int>()
        StepVerifier.create(testPublisher.flux())
            .then { testPublisher.assertWasSubscribed() } // why not subscriber test cases like flux concat
            .then { testPublisher.emit(0, 1, 2) }
            .expectNext(1, 4, 9)
            .expectComplete()
            .verify()
    }

    // complex business logic
    private fun process(flux: Flux<Int>) {
        flux
            .map { i: Int -> i + 1 }
            .map { i: Int -> i * i }
            .take(3)
            .subscribe() // db / log files
    }
}
