package de.steklopod.sec11.assignment

import reactor.core.publisher.Flux
import reactor.core.publisher.Sinks
import reactor.core.publisher.Sinks.Many

class SlackRoom(private val name: String) {

    private val sink: Many<SlackMessage> = Sinks.many().replay().all()
    private val flux: Flux<SlackMessage> = sink.asFlux()

    fun joinRoom(slackMember: SlackMember) {
        println(slackMember.name + "------------- Joined ---------------" + name)
        subscribe(slackMember)
        slackMember.setMessageConsumer { msg: String -> postMessage(msg, slackMember) }
    }

    private fun subscribe(slackMember: SlackMember) {
        flux
            .filter { sm: SlackMessage -> sm.sender != slackMember.name }
            .doOnNext { sm: SlackMessage -> sm.receiver = slackMember.name }
            .map { obj: SlackMessage -> obj.toString() }
            .subscribe { message: String -> slackMember.receives(message) }
    }

    private fun postMessage(msg: String, slackMember: SlackMember) {
        val slackMessage = SlackMessage(sender = slackMember.name, message = msg)

        sink.tryEmitNext(slackMessage)
    }
}
