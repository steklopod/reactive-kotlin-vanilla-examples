package de.steklopod.sec12.helper

import reactor.util.context.Context
import java.util.function.Function

object UserService {
    private val MAP = mapOf("sam" to "std", "mike" to "prime")

    fun userCategoryContext(): Function<Context, Context> = Function { ctx: Context ->
        val user = ctx.get<String>("user").toString()

        val category = MAP[user]

        ctx.put("category", category!!)
    }
}
