/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

/**
 * Advent of Code 2022, Day 23 - Unstable Diffusion
 * Problem Description: http://adventofcode.com/2022/day/23
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2022/day23/
 */
package com.ginsberg.advent2022

class Day23(input: List<String>) {

    private val startingPositions = parseInput(input)
    private val nextTurnOffsets: List<List<Point2D>> = createOffsets()

    fun solvePart1(): Int {
        val locations = (0 until 10).fold(startingPositions) { carry, round -> carry.playRound(round) }
        val gridSize = ((locations.maxOf { it.x } - locations.minOf { it.x }) + 1) * ((locations.maxOf { it.y } - locations.minOf { it.y }) + 1)
        return gridSize - locations.size
    }

    fun solvePart2(): Int {
        var thisTurn = startingPositions
        var roundId = 0
        do {
            val previousTurn = thisTurn
            thisTurn = previousTurn.playRound(roundId++)
        } while (previousTurn != thisTurn)
        return roundId
    }

    private fun Set<Point2D>.playRound(roundNumber: Int): Set<Point2D> {
        val nextPositions = this.toMutableSet()
        val movers: Map<Point2D, Point2D> = this
            .filter { elf -> elf.neighbors().any { it in this } }
            .mapNotNull { elf ->
                nextTurnOffsets.indices.map { direction -> nextTurnOffsets[(roundNumber + direction) % 4] }
                    .firstNotNullOfOrNull { offsets ->
                        if (offsets.none { offset -> (elf + offset) in this }) elf to (elf + offsets.first())
                        else null
                    }
            }.toMap()

        val safeDestinations = movers.values.groupingBy { it }.eachCount().filter { it.value == 1 }.keys
        movers
            .filter { (_, target) -> target in safeDestinations }
            .forEach { (source, target) ->
                nextPositions.remove(source)
                nextPositions.add(target)
            }
        return nextPositions
    }

    private fun createOffsets(): List<List<Point2D>> =
        listOf(
            listOf(Point2D(0, -1), Point2D(-1, -1), Point2D(1, -1)), // N
            listOf(Point2D(0, 1), Point2D(-1, 1), Point2D(1, 1)), // S
            listOf(Point2D(-1, 0), Point2D(-1, -1), Point2D(-1, 1)), // W
            listOf(Point2D(1, 0), Point2D(1, -1), Point2D(1, 1)), // E
        )

    private fun parseInput(input: List<String>): Set<Point2D> =
        input.flatMapIndexed { y, row ->
            row.mapIndexedNotNull { x, char ->
                if (char == '#') Point2D(x, y) else null
            }
        }.toSet()
}
