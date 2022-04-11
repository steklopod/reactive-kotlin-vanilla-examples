package de.steklopod.sec11.assignment

import java.util.function.Consumer

class SlackMember(val name: String) {
    private var messageConsumer: Consumer<String>? = null

    fun receives(message: String?) {
        println(message)
    }

    fun says(message: String) {
        messageConsumer!!.accept(message)
    }

    fun setMessageConsumer(messageConsumer: Consumer<String>?) {
        this.messageConsumer = messageConsumer
    }
}
