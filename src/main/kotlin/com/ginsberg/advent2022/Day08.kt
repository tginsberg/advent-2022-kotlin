/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

/**
 * Advent of Code 2022, Day 8 - Treetop Tree House
 * Problem Description: http://adventofcode.com/2022/day/8
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2022/day8/
 */
package com.ginsberg.advent2022

class Day08(input: List<String>) {

    private val grid: Array<IntArray> = parseInput(input)
    private val gridHeight: Int = grid.size
    private val gridWidth: Int = grid.first().size

    fun solvePart1(): Int = (1 until gridHeight - 1).sumOf { y ->
        (1 until gridWidth - 1).count { x ->
            grid.isVisible(x, y)
        }
    } + (2 * gridHeight) + (2 * gridWidth) - 4

    fun solvePart2(): Int = (1 until gridHeight - 1).maxOf { y ->
        (1 until gridWidth - 1).maxOf { x ->
            grid.scoreAt(x, y)
        }
    }

    private fun Array<IntArray>.isVisible(x: Int, y: Int): Boolean =
        viewFrom(x, y).any { direction ->
            direction.all { it < this[y][x] }
        }

    private fun Array<IntArray>.scoreAt(x: Int, y: Int): Int =
        viewFrom(x, y).map { direction ->
            direction.takeUntil { it >= this[y][x] }.count()
        }.product()

    private fun Array<IntArray>.viewFrom(x: Int, y: Int): List<List<Int>> =
        listOf(
            (y - 1 downTo 0).map { this[it][x] }, // Up
            (y + 1 until gridHeight).map { this[it][x] }, // Down
            this[y].take(x).asReversed(), // Left
            this[y].drop(x + 1) // Right
        )

    private fun parseInput(input: List<String>): Array<IntArray> = input.map { row ->
        row.map(Char::digitToInt).toIntArray()
    }.toTypedArray()
}
