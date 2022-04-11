package de.steklopod.sec09.helper

import de.steklopod.utils.Util.faker


data class BookOrder(
    val title: String = faker().book().title(),
    val author: String = faker().book().author(),
    val category: String = faker().book().genre(),
    val price: Double = faker().commerce().price().replace(",",".").toDouble()
)
