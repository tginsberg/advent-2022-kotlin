/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

/**
 * Advent of Code 2022, Day 1 - Calorie Counting
 * Problem Description: http://adventofcode.com/2022/day/1
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2022/day1/
 */
package com.ginsberg.advent2022

class Day01(input: String) {

    private val calories = parseInput(input)

    fun solvePart1(): Int =
        calories.first()

    fun solvePart2(): Int =
        calories.take(3).sum()

    private fun parseInput(input: String): List<Int> =
        input
            .trim()
            .split("\n\n")
            .map { it.lines().sumOf(String::toInt) }
            .sortedDescending()

}