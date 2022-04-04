package de.steklopod.sec01

import de.steklopod.utils.Util
import de.steklopod.sec01.assignment.FileService

object Lec09AssignmentDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        FileService.write("file03.txt", "This is file3")
            .subscribe(Util.onNext(), Util.onError(), Util.onComplete())
        FileService.read("file03.txt")
            .subscribe(Util.onNext(), Util.onError(), Util.onComplete())
        FileService.delete("file03.txt")
            .subscribe(Util.onNext(), Util.onError(), Util.onComplete())
    }
}
