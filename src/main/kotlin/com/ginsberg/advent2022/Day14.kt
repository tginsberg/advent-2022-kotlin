/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

/**
 * Advent of Code 2022, Day 14 - Regolith Reservoir
 * Problem Description: http://adventofcode.com/2022/day/14
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2022/day14/
 */
package com.ginsberg.advent2022

class Day14(input: List<String>) {

    private val cave: MutableSet<Point2D> = parseInput(input)
    private val sandSource: Point2D = Point2D(500, 0)
    private val maxY: Int = cave.maxOf { it.y }

    fun solvePart1(): Int =
        dropSand(maxY + 1)

    fun solvePart2(): Int {
        val minX: Int = cave.minOf { it.x }
        val maxX: Int = cave.maxOf { it.x }
        cave.addAll(Point2D(minX - maxY, maxY + 2).lineTo(Point2D(maxX + maxY, maxY + 2)))
        return dropSand(maxY + 3) + 1
    }

    private fun dropSand(voidStartsAt: Int): Int {
        var start = sandSource
        var landed = 0
        while (true) {
            val next = listOf(start.down(), start.downLeft(), start.downRight()).firstOrNull { it !in cave }
            start = when {
                next == null && start == sandSource -> return landed
                next == null -> {
                    cave.add(start)
                    landed += 1
                    sandSource
                }

                next.y == voidStartsAt -> return landed
                else -> next
            }
        }
    }

    private fun Point2D.down(): Point2D = Point2D(x, y + 1)
    private fun Point2D.downLeft(): Point2D = Point2D(x - 1, y + 1)
    private fun Point2D.downRight(): Point2D = Point2D(x + 1, y + 1)

    private fun parseInput(input: List<String>): MutableSet<Point2D> =
        input.flatMap { row ->
            row.split(" -> ")
                .map { Point2D.of(it) }
                .zipWithNext()
                .flatMap { (from, to) ->
                    from.lineTo(to)
                }
        }.toMutableSet()
}
