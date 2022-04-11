package de.steklopod.sec11

import de.steklopod.sec11.assignment.SlackMember
import de.steklopod.sec11.assignment.SlackRoom
import de.steklopod.utils.Util.sleepSeconds

object Lec07SlackDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        val slackRoom = SlackRoom("reactor")
        val sam = SlackMember("sam")
        val jake = SlackMember("jake")
        val mike = SlackMember("mike")
        slackRoom.joinRoom(sam)
        slackRoom.joinRoom(jake)
        sam.says("Hi all..")
        sleepSeconds(4)
        jake.says("Hey!")
        sam.says("I simply wanted to say hi..")
        sleepSeconds(4)
        slackRoom.joinRoom(mike)
        mike.says("Hey guys..glad to be here...")
    }
}
