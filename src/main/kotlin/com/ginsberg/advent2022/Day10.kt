/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

/**
 * Advent of Code 2022, Day 10 - Cathode-Ray Tube
 * Problem Description: http://adventofcode.com/2022/day/10
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2022/day10/
 */
package com.ginsberg.advent2022

import kotlin.math.absoluteValue

class Day10(input: List<String>) {

    private val signals: List<Int> = parseInput(input).runningReduce(Int::plus)

    fun solvePart1(): Int =
        signals.sampleSignals().sum()

    fun solvePart2(): Unit =
        signals.screen().print()

    private fun List<Int>.sampleSignals(): List<Int> =
         (60 .. size step 40).map {cycle ->
            cycle * this[cycle - 1]
        } + this[19] * 20

    private fun List<Int>.screen(): List<Boolean> =
        this.mapIndexed { pixel, signal ->
            (signal-(pixel%40)).absoluteValue <= 1
        }

    private fun List<Boolean>.print() {
        this.windowed(40, 40, false).forEach { row ->
            row.forEach { pixel ->
                print(if(pixel) '#' else ' ')
            }
            println()
        }
    }

    private fun parseInput(input: List<String>): List<Int> =
        buildList {
            add(1)
            input.forEach { line ->
                add(0)
                if (line.startsWith("addx")) {
                    add(line.substringAfter(" ").toInt())
                }
            }
        }

}
