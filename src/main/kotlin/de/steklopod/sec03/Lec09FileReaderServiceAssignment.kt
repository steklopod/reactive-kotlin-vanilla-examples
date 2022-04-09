package de.steklopod.sec03

import de.steklopod.sec03.assignment.FileReaderService
import de.steklopod.utils.Util.faker
import de.steklopod.utils.Util.subscriber
import java.nio.file.Paths

object Lec09FileReaderServiceAssignment {
    @JvmStatic
    fun main(args: Array<String>) {
        val readerService = FileReaderService()

        val path = Paths.get("src/main/resources/assignment/sec03/file01.txt")

        readerService.read(path)
            .map { s: String? ->
                val integer = faker().random().nextInt(0, 10)
                if (integer > 8) throw RuntimeException("oops")
                s!!
            }
            .take(20)
            .subscribe(subscriber())
    }
}
