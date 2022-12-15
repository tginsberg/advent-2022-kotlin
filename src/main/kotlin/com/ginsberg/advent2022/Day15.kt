/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

/**
 * Advent of Code 2022, Day 15 - Beacon Exclusion Zone
 * Problem Description: http://adventofcode.com/2022/day/15
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2022/day15/
 */
package com.ginsberg.advent2022

import kotlin.math.absoluteValue

class Day15(input: List<String>) {

    private val sensors: Set<Sensor> = parseInput(input)

    fun solvePart1(findRow: Int): Int =
        sensors.mapNotNull { it.findRange(findRow) }
            .reduce()
            .sumOf { it.last - it.first }

    fun solvePart2(caveSize: Int): Long {
        val cave = (0..caveSize)
        return sensors.firstNotNullOf { sensor ->
            val up = Point2D(sensor.location.x, sensor.location.y - sensor.distance - 1)
            val down = Point2D(sensor.location.x, sensor.location.y + sensor.distance + 1)
            val left = Point2D(sensor.location.x - sensor.distance - 1, sensor.location.y)
            val right = Point2D(sensor.location.x + sensor.distance + 1, sensor.location.y)

            (up.lineTo(right) + right.lineTo(down) + down.lineTo(left) + left.lineTo(up))
                .filter { it.x in cave && it.y in cave }
                .firstOrNull { candidate -> sensors.none { sensor -> sensor.isInRange(candidate) } }
        }.calculateAnswer()
    }

    private fun Point2D.calculateAnswer(): Long = (x * 4000000L) + y

    private class Sensor(val location: Point2D, closestBeacon: Point2D) {
        val distance: Int = location.distanceTo(closestBeacon)

        fun findRange(y: Int): IntRange? {
            val scanWidth = distance - (location.y - y).absoluteValue
            return (location.x - scanWidth..location.x + scanWidth).takeIf { it.first <= it.last }
        }

        fun isInRange(other: Point2D): Boolean =
            location.distanceTo(other) <= distance
    }

    private fun parseInput(input: List<String>): Set<Sensor> =
        input
            .map { row ->
                Sensor(
                    Point2D(
                        row.substringAfter("x=").substringBefore(",").toInt(),
                        row.substringAfter("y=").substringBefore(":").toInt()
                    ),
                    Point2D(
                        row.substringAfterLast("x=").substringBefore(",").toInt(),
                        row.substringAfterLast("=").toInt()
                    )
                )
            }.toSet()

}
