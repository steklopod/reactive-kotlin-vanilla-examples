package de.steklopod.sec12

import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Mono
import reactor.util.context.Context
import reactor.util.context.ContextView
import java.util.*

object Lec01Ctx {
    @JvmStatic
    fun main(args: Array<String>) {
        welcomeMessage()
            .contextWrite { ctx: Context ->
                ctx.put("user", ctx.get<String>("user").capitalize())
            }
            .contextWrite(Context.of("user", "sam"))
            .subscribe(subscriber())
    }

    private fun welcomeMessage(): Mono<String> = Mono.deferContextual { ctx: ContextView ->
        if (ctx.hasKey("user")) Mono.just("👋🏻 Welcome  " + ctx.get("user"))
        else Mono.error<String>(RuntimeException("unauthenticated"))
    }
}
