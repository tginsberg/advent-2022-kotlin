/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

/**
 * Advent of Code 2022, Day 2 - Rock Paper Scissors
 * Problem Description: http://adventofcode.com/2022/day/2
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2022/day2/
 */
package com.ginsberg.advent2022

class Day02(private val input: List<String>) {

    private val part1Scores: Map<String, Int> =
        mapOf(
            "A X" to 1 + 3,
            "A Y" to 2 + 6,
            "A Z" to 3 + 0,
            "B X" to 1 + 0,
            "B Y" to 2 + 3,
            "B Z" to 3 + 6,
            "C X" to 1 + 6,
            "C Y" to 2 + 0,
            "C Z" to 3 + 3,
        )

    private val part2Scores: Map<String, Int> =
        mapOf(
            "A X" to 3 + 0,
            "A Y" to 1 + 3,
            "A Z" to 2 + 6,
            "B X" to 1 + 0,
            "B Y" to 2 + 3,
            "B Z" to 3 + 6,
            "C X" to 2 + 0,
            "C Y" to 3 + 3,
            "C Z" to 1 + 6,
        )

    fun solvePart1(): Int =
        input.sumOf { part1Scores[it] ?: 0 }

    fun solvePart2(): Int =
        input.sumOf { part2Scores[it] ?: 0 }
}