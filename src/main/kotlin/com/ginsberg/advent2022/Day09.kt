/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

/**
 * Advent of Code 2022, Day 9 - Rope Bridge
 * Problem Description: http://adventofcode.com/2022/day/9
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2022/day9/
 */
package com.ginsberg.advent2022

import kotlin.math.absoluteValue
import kotlin.math.sign

class Day09(input: List<String>) {

    private val headPath: String = parseInput(input)

    fun solvePart1(): Int =
        followPath(2)

    fun solvePart2(): Int =
        followPath(10)

    private fun followPath(knots: Int): Int {
        val rope = Array(knots) { Point() }
        val tailVisits = mutableSetOf(Point())

        headPath.forEach { direction ->
            rope[0] = rope[0].move(direction)
            rope.indices.windowed(2, 1) { (head, tail) ->
                if (!rope[head].touches(rope[tail])) {
                    rope[tail] = rope[tail].moveTowards(rope[head])
                }
            }
            tailVisits += rope.last()
        }
        return tailVisits.size
    }

    private data class Point(val x: Int = 0, val y: Int = 0) {
        fun move(direction: Char): Point =
            when (direction) {
                'U' -> copy(y = y - 1)
                'D' -> copy(y = y + 1)
                'L' -> copy(x = x - 1)
                'R' -> copy(x = x + 1)
                else -> throw IllegalArgumentException("Unknown Direction: $direction")
            }

        fun moveTowards(other: Point): Point =
            Point(
                (other.x - x).sign + x,
                (other.y - y).sign + y
            )

        fun touches(other: Point): Boolean =
            (x - other.x).absoluteValue <= 1 && (y - other.y).absoluteValue <= 1

    }

    private fun parseInput(input: List<String>): String =
        input.joinToString("") { row ->
            val direction = row.substringBefore(" ")
            val numberOfMoves = row.substringAfter(' ').toInt()
            direction.repeat(numberOfMoves)
        }

}
