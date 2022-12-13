package com.ginsberg.advent2022


// Modified version of takeWhile from the Kotlin Standard Library
inline fun <T> Iterable<T>.takeUntil(predicate: (T) -> Boolean): List<T> {
    val list = ArrayList<T>()
    for (item in this) {
        list.add(item)
        if (predicate(item))
            break
    }
    return list
}

fun Iterable<Int>.product(): Int =
    reduce { a, b -> a * b }
