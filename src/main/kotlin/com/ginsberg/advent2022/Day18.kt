/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

/**
 * Advent of Code 2022, Day 18 - Boiling Boulders
 * Problem Description: http://adventofcode.com/2022/day/18
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2022/day187/
 */
package com.ginsberg.advent2022

class Day18(input: List<String>) {

    private val points: Set<Point3D> = input.map { Point3D.of(it) }.toSet()

    fun solvePart1(): Int =
        points.sumOf { point ->
            6 - point.cardinalNeighbors().count { neighbor -> neighbor in points }
        }

    fun solvePart2(): Int {
        val xRange = points.rangeOf { it.x }
        val yRange = points.rangeOf { it.y }
        val zRange = points.rangeOf { it.z }

        val queue = ArrayDeque<Point3D>().apply { add(Point3D(xRange.first, yRange.first, zRange.first)) }
        val seen = mutableSetOf<Point3D>()
        var sidesFound = 0
        queue.forEach { lookNext ->
            if (lookNext !in seen) {
                lookNext.cardinalNeighbors()
                    .filter { it.x in xRange && it.y in yRange && it.z in zRange }
                    .forEach { neighbor ->
                        seen += lookNext
                        if (neighbor in points) sidesFound++
                        else queue.add(neighbor)
                    }
            }
        }
        return sidesFound
    }

    private fun Set<Point3D>.rangeOf(function: (Point3D) -> Int): IntRange =
        this.minOf(function) - 1..this.maxOf(function) + 1
}
