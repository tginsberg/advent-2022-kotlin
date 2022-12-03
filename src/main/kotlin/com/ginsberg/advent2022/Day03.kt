/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

/**
 * Advent of Code 2022, Day 3 - Rucksack Reorganization
 * Problem Description: http://adventofcode.com/2022/day/3
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2022/day3/
 */
package com.ginsberg.advent2022

class Day03(private val input: List<String>) {

    fun solvePart1(): Int =
        input.sumOf { it.sharedItem().priority() }

    fun solvePart2(): Int =
        input.chunked(3).sumOf { it.sharedItem().priority() }

    private fun Char.priority(): Int =
        when (this) {
            in 'a'..'z' -> (this - 'a') + 1
            in 'A'..'Z' -> (this - 'A') + 27
            else -> throw IllegalArgumentException("Letter not in range: $this")
        }

    private fun String.sharedItem(): Char =
        listOf(
            substring(0..length / 2),
            substring(length / 2)
        ).sharedItem()

    private fun List<String>.sharedItem(): Char =
        map { it.toSet() }
            .reduce { left, right -> left intersect right}
            .first()
}
