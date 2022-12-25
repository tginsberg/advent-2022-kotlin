/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

/**
 * Advent of Code 2022, Day 25 - Full of Hot Air
 * Problem Description: http://adventofcode.com/2022/day/24
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2022/day24/
 */
package com.ginsberg.advent2022

class Day25(private val input: List<String>) {

    fun solvePart1(): String=
        input.sumOf { it.fromSnafu() }.toSnafu()

    private fun String.fromSnafu(): Long =
        fold(0) { carry, char ->
            (carry * 5) + when(char) {
                '-' -> -1
                '=' -> -2
                else -> char.digitToInt()
            }
        }

    private fun Long.toSnafu(): String =
        generateSequence(this) { (it + 2) / 5 }
            .takeWhile { it != 0L }
            .map { "012=-"[(it % 5).toInt()] }
            .joinToString("")
            .reversed()

}
