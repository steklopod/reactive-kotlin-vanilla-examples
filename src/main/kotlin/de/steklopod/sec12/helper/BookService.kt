package de.steklopod.sec12.helper

import de.steklopod.utils.Util.faker
import reactor.core.publisher.Mono
import reactor.util.context.Context
import reactor.util.context.ContextView
import java.util.function.Function

object BookService {
    private val map: MutableMap<String, Int> = mutableMapOf("std" to 2, "prime" to 3)

    fun book(): Mono<String> = Mono.deferContextual { ctx: ContextView ->
        if (ctx.get("allow")) Mono.just(faker().book().title())
        else Mono.error(RuntimeException("not-allowed"))
    }.contextWrite{ctx: Context -> rateLimiterContext(ctx) }

    private fun rateLimiterContext(ctx: Context): Context {
        if (ctx.hasKey("category")) {
            val category = ctx.get<String>("category").toString()
            val attempts = map[category]
            if (attempts!! > 0) {
                map[category] = attempts - 1
                ctx.put("allow", true)
            }
        } else ctx.put("allow", false)
        return ctx
    }
}
