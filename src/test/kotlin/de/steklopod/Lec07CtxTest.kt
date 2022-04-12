package de.steklopod

import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import reactor.test.StepVerifierOptions
import reactor.util.context.Context
import reactor.util.context.ContextView

class Lec07CtxTest {
    @Test
    fun test1() {
        StepVerifier.create(welcomeMessage)
            .verifyError(RuntimeException::class.java)
    }

    @Test
    fun test2() {
        val options = StepVerifierOptions.create().withInitialContext(Context.of("user", "sam"))
        StepVerifier.create(welcomeMessage, options)
            .expectNext("Welcome sam")
            .verifyComplete()
    }

    private val welcomeMessage: Mono<String>
        private get() = Mono.deferContextual { ctx: ContextView ->
            if (ctx.hasKey("user")) {
                return@deferContextual Mono.just("Welcome " + ctx.get("user"))
            } else {
                return@deferContextual Mono.error<String>(RuntimeException("unauthenticated"))
            }
        }
}
