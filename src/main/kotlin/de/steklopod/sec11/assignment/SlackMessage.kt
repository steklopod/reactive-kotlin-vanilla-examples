package de.steklopod.sec11.assignment

data class SlackMessage (
     val sender: String,
     val message: String,
     var receiver: String? = null,
){
    override fun toString(): String ="[$sender -> $receiver] : $message"
}
