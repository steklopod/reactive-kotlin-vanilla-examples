package de.steklopod.sec11.assignment

class SlackMember(val name: String) {

    fun receives(message: String) {
        println("received message 📥 : $message")
    }

    fun says(msg: String) {
        val slackMessage = SlackMessage(sender = name, message = msg)

        SlackRoom.sink
            .tryEmitNext(slackMessage)
    }

}
