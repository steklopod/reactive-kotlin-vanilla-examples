package de.steklopod.sec03.assignment

import reactor.core.publisher.Flux
import reactor.core.publisher.SynchronousSink
import java.io.BufferedReader
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.util.*
import java.util.concurrent.Callable
import java.util.function.BiFunction
import java.util.function.Consumer

class FileReaderService {
    private fun openReader(path: Path): Callable<BufferedReader> {
        return Callable { Files.newBufferedReader(path) }
    }

    private fun read(): BiFunction<BufferedReader, SynchronousSink<String?>, BufferedReader> {
        return BiFunction { br: BufferedReader, sink: SynchronousSink<String?> ->
            try {
                val line = br.readLine()
                println("reading --- $line")
                if (Objects.isNull(line)) sink.complete() else sink.next(line)
            } catch (e: IOException) {
                sink.error(e)
            }
            br
        }
    }

    private fun closeReader(): Consumer<BufferedReader> {
        return Consumer { br: BufferedReader ->
            try {
                br.close()
                println("--closed")
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun read(path: Path): Flux<String?> {
        return Flux.generate(
            openReader(path),
            read(),
            closeReader()
        )
    }
}
