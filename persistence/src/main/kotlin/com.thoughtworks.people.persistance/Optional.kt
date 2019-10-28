package com.thoughtworks.people.persistance

import java.util.*

fun <T: Any> Optional<T>.toNullable(): T? =
        this.orElse(null)