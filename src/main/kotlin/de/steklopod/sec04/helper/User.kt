package de.steklopod.sec04.helper

import de.steklopod.utils.Util.faker


data class User(
    val userId: Int,
    val name: String = faker().name().fullName()
)
