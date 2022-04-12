package de.steklopod.sec11.assignment

import reactor.core.publisher.Flux
import reactor.core.publisher.Sinks
import reactor.core.publisher.Sinks.Many

class SlackRoom(private val name: String) {

    fun joinRoom(member: SlackMember) {
        println(member.name + "------------- Joined --------------- [$name]")

        messages
            .filter { it.sender != member.name }
            .doOnNext { it.receiver = member.name }
            .map(SlackMessage::toString)
            .subscribe { member.receives(it) }
    }

    companion object {
        val sink: Many<SlackMessage> = Sinks.many()
            .replay()
            .all()

        private val messages: Flux<SlackMessage> = sink.asFlux()
    }

}
