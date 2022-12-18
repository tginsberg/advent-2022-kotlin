/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

/**
 * Advent of Code 2022, Day 17 - Pyroclastic Flow
 * Problem Description: http://adventofcode.com/2022/day/17
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2022/day17/
 */
package com.ginsberg.advent2022

import kotlin.math.absoluteValue

class Day17(input: String) {

    private val jets: List<Point2D> = parseJets(input)
    private val shapes: List<Set<Point2D>> = generateShapes()
    private val cave: MutableSet<Point2D> = (0..6).map { Point2D(it, 0) }.toMutableSet()
    private val down: Point2D = Point2D(0, 1)
    private val up: Point2D = Point2D(0, -1)
    private var jetCounter: Int = 0
    private var blockCounter: Int = 0

    fun solvePart1(): Int {
        repeat(2022) {
            simulate()
        }
        return cave.height()
    }

    fun solvePart2(): Long =
        calculateHeight(1000000000000L - 1)

    private fun simulate() {
        var thisShape = shapes.nth(blockCounter++).moveToStart(cave.minY())
        do {
            val jettedShape = thisShape * jets.nth(jetCounter++)
            if (jettedShape in (0..6) && jettedShape.intersect(cave).isEmpty()) {
                thisShape = jettedShape
            }
            thisShape = thisShape * down
        } while (thisShape.intersect(cave).isEmpty())
        cave += (thisShape * up)
    }

    private fun calculateHeight(targetBlockCount: Long): Long {
        data class State(val ceiling: List<Int>, val blockMod: Int, val jetMod: Int)

        val seen: MutableMap<State, Pair<Int, Int>> = mutableMapOf()
        while (true) {
            simulate()
            val state = State(cave.normalizedCaveCeiling(), blockCounter % shapes.size, jetCounter % jets.size)
            if (state in seen) {
                // Fast forward
                val (blockCountAtLoopStart, heightAtLoopStart) = seen.getValue(state)
                val blocksPerLoop: Long = blockCounter - 1L - blockCountAtLoopStart
                val totalLoops: Long = (targetBlockCount - blockCountAtLoopStart) / blocksPerLoop
                val remainingBlocksFromClosestLoopToGoal: Long =
                    (targetBlockCount - blockCountAtLoopStart) - (totalLoops * blocksPerLoop)
                val heightGainedSinceLoop = cave.height() - heightAtLoopStart
                repeat(remainingBlocksFromClosestLoopToGoal.toInt()) {
                    simulate()
                }
                return cave.height() + (heightGainedSinceLoop * (totalLoops - 1))
            }
            seen[state] = blockCounter - 1 to cave.height()
        }
    }

    private operator fun IntRange.contains(set: Set<Point2D>): Boolean = set.all { it.x in this }
    private operator fun Set<Point2D>.times(point: Point2D): Set<Point2D> = map { it + point }.toSet()
    private fun Set<Point2D>.minY(): Int = minOf { it.y }
    private fun Set<Point2D>.height(): Int = minY().absoluteValue

    private fun Set<Point2D>.normalizedCaveCeiling(): List<Int> =
        groupBy { it.x }
            .entries
            .sortedBy { it.key }
            .map { pointList -> pointList.value.minBy { point -> point.y } }
            .let {
                val normalTo = this.minY()
                it.map { point -> normalTo - point.y }
            }

    private fun Set<Point2D>.moveToStart(ceilingHeight: Int): Set<Point2D> =
        map { it + Point2D(2, ceilingHeight - 4) }.toSet()

    private fun generateShapes(): List<Set<Point2D>> =
        listOf(
            setOf(Point2D(0, 0), Point2D(1, 0), Point2D(2, 0), Point2D(3, 0)),
            setOf(Point2D(1, 0), Point2D(0, -1), Point2D(1, -1), Point2D(2, -1), Point2D(1, -2)),
            setOf(Point2D(0, 0), Point2D(1, 0), Point2D(2, 0), Point2D(2, -1), Point2D(2, -2)),
            setOf(Point2D(0, 0), Point2D(0, -1), Point2D(0, -2), Point2D(0, -3)),
            setOf(Point2D(0, 0), Point2D(1, 0), Point2D(0, -1), Point2D(1, -1))
        )

    private fun parseJets(input: String): List<Point2D> =
        input.map {
            when (it) {
                '>' -> Point2D(1, 0)
                '<' -> Point2D(-1, 0)
                else -> throw IllegalStateException("No such jet direction $it")
            }
        }


}
