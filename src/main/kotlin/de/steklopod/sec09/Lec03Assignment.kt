package de.steklopod.sec09

import de.steklopod.sec09.helper.BookOrder
import de.steklopod.sec09.helper.RevenueReport
import de.steklopod.utils.Util.sleepSeconds
import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Flux
import java.time.Duration
import java.util.stream.Collectors

object Lec03Assignment {
    private val allowedCategories = mutableSetOf("Science fiction", "Fantasy", "Suspense/Thriller")

    @JvmStatic
    fun main(args: Array<String>) {
        bookStream()
            .filter { book: BookOrder -> allowedCategories.contains(book.category) }
            .buffer(Duration.ofSeconds(5))
            .map { list: List<BookOrder> -> revenueCalculator(list) }
            .subscribe(subscriber())

        sleepSeconds(60)
    }

    private fun revenueCalculator(books: List<BookOrder>): RevenueReport {
        val map = books.stream()
            .collect(
                Collectors.groupingBy(
                    { obj: BookOrder -> obj.category },
                    Collectors.summingDouble { obj: BookOrder -> obj.price }
                )
            )
        return RevenueReport(revenue = map)
    }

    private fun bookStream(): Flux<BookOrder> = Flux.interval(Duration.ofMillis(200))
        .map { BookOrder() }
}
