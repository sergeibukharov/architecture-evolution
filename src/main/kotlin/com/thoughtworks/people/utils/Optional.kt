package com.thoughtworks.people.utils

import java.util.*

fun <T: Any> Optional<T>.toNullable(): T? =
        this.orElse(null)