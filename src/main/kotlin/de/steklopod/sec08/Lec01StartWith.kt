package de.steklopod.sec08

import de.steklopod.sec08.helper.NameGenerator
import de.steklopod.utils.Util.subscriber

object Lec01StartWith {
    @JvmStatic
    fun main(args: Array<String>) {
        val generator = NameGenerator()

        generator.generateNames()
            .take(2)
            .subscribe(subscriber("sam"))

        generator.generateNames()
            .take(2)
            .subscribe(subscriber("mike"))

        generator.generateNames()
            .take(3)
            .subscribe(subscriber("Jake"))

        generator.generateNames()
            .filter { n: String -> n.startsWith("A") }
            .take(2)
            .subscribe(subscriber("Marshal"))
    }
}
