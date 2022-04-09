package de.steklopod.sec04.helper

import reactor.core.publisher.Flux

object UserService {
    fun getUsers(): Flux<User> = Flux.range(1, 2)
        .map { i: Int -> User(i) }
}
