/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

/**
 * Advent of Code 2022, Day 6 - Tuning Trouble
 * Problem Description: http://adventofcode.com/2022/day/6
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2022/day6/
 */
package com.ginsberg.advent2022

class Day06(private val input: String) {

    fun solvePart1(): Int =
        input.findStartMarker(4)

    fun solvePart2(): Int =
        input.findStartMarker(14)

    private fun String.findStartMarker(startMarkerSize: Int): Int =
        withIndex()
            .windowed(startMarkerSize, 1)
            .first { window ->
                window.map { it.value }.toSet().size == startMarkerSize
            }.last().index + 1
}
