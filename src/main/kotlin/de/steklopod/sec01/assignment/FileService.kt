package de.steklopod.sec01.assignment

import reactor.core.publisher.Mono
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

object FileService {
    private val PATH = Paths.get("src/main/resources/assignment/sec01")
    @JvmStatic
    fun read(fileName: String): Mono<String> {
        return Mono.fromSupplier { readFile(fileName) }
    }

    @JvmStatic
    fun write(fileName: String, content: String): Mono<Void> {
        return Mono.fromRunnable { writeFile(fileName, content) }
    }

    @JvmStatic
    fun delete(fileName: String): Mono<Void> {
        return Mono.fromRunnable { deleteFile(fileName) }
    }

    private fun readFile(fileName: String): String {
        return try {
            Files.readString(PATH.resolve(fileName))
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    private fun writeFile(fileName: String, content: String) {
        try {
            Files.writeString(PATH.resolve(fileName), content, StandardOpenOption.CREATE, StandardOpenOption.APPEND)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    private fun deleteFile(fileName: String) {
        try {
            Files.delete(PATH.resolve(fileName))
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }
}
