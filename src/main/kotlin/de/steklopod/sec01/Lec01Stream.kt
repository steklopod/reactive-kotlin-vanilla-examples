package de.steklopod.sec01

import java.util.stream.Stream

object Lec01Stream {
    @JvmStatic
    fun main(args: Array<String>) {
        val stream = Stream.of(1)
            .map { i: Int ->
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                i * 2
            }

        //System.out.println(stream);
        stream.forEach { x: Int -> println(x) }
    }
}
