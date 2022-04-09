package de.steklopod.sec04

import de.steklopod.sec04.helper.OrderService
import de.steklopod.sec04.helper.User
import de.steklopod.sec04.helper.UserService
import de.steklopod.utils.Util.sleepSeconds
import de.steklopod.utils.Util.subscriber
import java.io.BufferedReader

object Lec12FlatMap {
    @JvmStatic
    fun main(args: Array<String>) {
//        var reader: BufferedReader

        UserService.getUsers()
            .flatMap { user: User -> OrderService.getOrders(user.userId) }
            // .filter(p -> p > 10)
            .subscribe(subscriber())
        sleepSeconds(60)
    }
}
