package de.steklopod.sec12

import de.steklopod.sec12.helper.BookService
import de.steklopod.sec12.helper.UserService
import de.steklopod.utils.Util.subscriber
import reactor.util.context.Context

object Lec02CtxRateLimiterDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        BookService.book()
            .repeat(3)
            .contextWrite(UserService.userCategoryContext())
            .contextWrite(Context.of("user", "mike"))
            .subscribe(subscriber())
    }
}
