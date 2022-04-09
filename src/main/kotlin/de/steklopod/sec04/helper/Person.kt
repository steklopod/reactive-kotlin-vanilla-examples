package de.steklopod.sec04.helper

import de.steklopod.utils.Util.faker


data class Person(
    var name: String = faker().name().firstName(),
    val age: Int = faker().random().nextInt(1, 30)
)
