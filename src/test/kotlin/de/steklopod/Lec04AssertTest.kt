package de.steklopod

import de.steklopod.sec09.helper.BookOrder
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.time.Duration

class Lec04AssertTest {
    @Test
    fun test1() {
        val mono = Mono.fromSupplier { BookOrder() }

        StepVerifier.create(mono)
            .assertNext { assertNotNull(it.author) }
            .verifyComplete()
    }

    @Test
    fun test2() {
        val mono = Mono.fromSupplier { BookOrder() }
            .delayElement(Duration.ofSeconds(3))

        StepVerifier.create(mono)
            .assertNext { assertNotNull(it.author) }
            .expectComplete()
            .verify(Duration.ofSeconds(4))
    }
}
