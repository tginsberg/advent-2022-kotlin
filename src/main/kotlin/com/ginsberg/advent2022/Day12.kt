/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

/**
 * Advent of Code 2022, Day 12 - Hill Climbing Algorithm
 * Problem Description: http://adventofcode.com/2022/day/12
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2022/day12/
 */
package com.ginsberg.advent2022

import java.util.PriorityQueue

class Day12(input: List<String>) {

    private val heightMap: HeightMap = parseInput(input)

    fun solvePart1(): Int = heightMap.shortestPath(
        begin = heightMap.start,
        isGoal = { it == heightMap.end },
        canMove = { from, to -> to - from <= 1 }
    )

    fun solvePart2(): Int = heightMap.shortestPath(
        begin = heightMap.end,
        isGoal = { heightMap.elevations[it] == 0 },
        canMove = { from, to -> from - to <= 1 }
    )

    private class HeightMap(val elevations: Map<Point2D, Int>, val start: Point2D, val end: Point2D) {

        fun shortestPath(
            begin: Point2D,
            isGoal: (Point2D) -> Boolean,
            canMove: (Int, Int) -> Boolean
        ): Int {
            val seen = mutableSetOf<Point2D>()
            val queue = PriorityQueue<PathCost>().apply { add(PathCost(begin, 0)) }

            while (queue.isNotEmpty()) {
                val nextPoint = queue.poll()

                if (nextPoint.point !in seen) {
                    seen += nextPoint.point
                    val neighbors = nextPoint.point.cardinalNeighbors()
                        .filter { it in elevations }
                        .filter { canMove(elevations.getValue(nextPoint.point), elevations.getValue(it)) }
                    if (neighbors.any { isGoal(it) }) return nextPoint.cost + 1
                    queue.addAll(neighbors.map { PathCost(it, nextPoint.cost + 1) })
                }
            }
            throw IllegalStateException("No valid path from $start to $end")
        }
    }

    private data class PathCost(val point: Point2D, val cost: Int) : Comparable<PathCost> {
        override fun compareTo(other: PathCost): Int =
            this.cost.compareTo(other.cost)
    }

    private fun parseInput(input: List<String>): HeightMap {
        var start: Point2D? = null
        var end: Point2D? = null
        val elevations = input.flatMapIndexed { y, row ->
            row.mapIndexed { x, char ->
                val here = Point2D(x, y)
                here to when (char) {
                    'S' -> 0.also { start = here }
                    'E' -> 25.also { end = here }
                    else -> char - 'a'
                }
            }
        }.toMap()
        return HeightMap(elevations, start!!, end!!)
    }
}

