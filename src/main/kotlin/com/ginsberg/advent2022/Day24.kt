/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

/**
 * Advent of Code 2022, Day 24 - Blizzard Basin
 * Problem Description: http://adventofcode.com/2022/day/24
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2022/day24/
 */
package com.ginsberg.advent2022

class Day24(input: List<String>) {

    private val initialMapState: MapState = MapState.of(input)
    private val start: Point2D = Point2D(input.first().indexOfFirst { it == '.' }, 0)
    private val goal: Point2D = Point2D(input.last().indexOfFirst { it == '.' }, input.lastIndex)

    fun solvePart1(): Int =
        solve().first

    fun solvePart2(): Int {
        val toGoal = solve()
        val backToStart = solve(goal, start, toGoal.second, toGoal.first)
        val backToGoal = solve(start, goal, backToStart.second, backToStart.first)
        return backToGoal.first
    }

    private fun solve(
        startPlace: Point2D = start,
        stopPlace: Point2D = goal,
        startState: MapState = initialMapState,
        stepsSoFar: Int = 0
    ): Pair<Int, MapState> {
        val mapStates = mutableMapOf(stepsSoFar to startState)
        val queue = mutableListOf(PathAttempt(stepsSoFar, startPlace))
        val seen = mutableSetOf<PathAttempt>()

        while (queue.isNotEmpty()) {
            val thisAttempt = queue.removeFirst()
            if (thisAttempt !in seen) {
                seen += thisAttempt
                val nextMapState = mapStates.computeIfAbsent(thisAttempt.steps + 1) { key ->
                    mapStates.getValue(key - 1).nextState()
                }

                // Can we just stand still here?
                if (nextMapState.isOpen(thisAttempt.location)) queue.add(thisAttempt.next())

                val neighbors = thisAttempt.location.cardinalNeighbors()

                // Is one of our neighbors the goal?
                if (stopPlace in neighbors) return Pair(thisAttempt.steps + 1, nextMapState)

                // Add neighbors that will be open to move to on the next turn.
                neighbors
                    .filter { it == start || (nextMapState.inBounds(it) && nextMapState.isOpen(it)) }
                    .forEach { neighbor ->
                        queue.add(thisAttempt.next(neighbor))
                    }
            }
        }
        throw IllegalStateException("No path to goal")
    }

    private data class PathAttempt(val steps: Int, val location: Point2D) {
        fun next(place: Point2D = location): PathAttempt =
            PathAttempt(steps + 1, place)
    }

    private data class MapState(val boundary: Point2D, val blizzards: Set<Blizzard>) {
        private val unsafeSpots = blizzards.map { it.location }.toSet()

        fun isOpen(place: Point2D): Boolean =
            place !in unsafeSpots

        fun inBounds(place: Point2D): Boolean =
            place.x > 0 && place.y > 0 && place.x <= boundary.x && place.y <= boundary.y

        fun nextState(): MapState =
            copy(blizzards = blizzards.map { it.next(boundary) }.toSet())

        companion object {
            fun of(input: List<String>): MapState =
                MapState(
                    Point2D(input.first().lastIndex - 1, input.lastIndex - 1),
                    input.flatMapIndexed { y, row ->
                        row.mapIndexedNotNull { x, char ->
                            when (char) {
                                '>' -> Blizzard(Point2D(x, y), Point2D(1, 0))
                                '<' -> Blizzard(Point2D(x, y), Point2D(-1, 0))
                                'v' -> Blizzard(Point2D(x, y), Point2D(0, 1))
                                '^' -> Blizzard(Point2D(x, y), Point2D(0, -1))
                                else -> null
                            }
                        }
                    }.toSet()
                )
        }
    }

    private data class Blizzard(val location: Point2D, val offset: Point2D) {
        fun next(boundary: Point2D): Blizzard {
            var nextLocation = location + offset
            when {
                nextLocation.x == 0 -> nextLocation = Point2D(boundary.x, location.y)
                nextLocation.x > boundary.x -> nextLocation = Point2D(1, location.y)
                nextLocation.y == 0 -> nextLocation = Point2D(location.x, boundary.y)
                nextLocation.y > boundary.y -> nextLocation = Point2D(location.x, 1)
            }
            return copy(location = nextLocation)
        }
    }
}
