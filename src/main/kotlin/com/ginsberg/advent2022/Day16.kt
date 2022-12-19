/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

/**
 * Advent of Code 2022, Day 16 - Proboscidea Volcanium
 * Problem Description: http://adventofcode.com/2022/day/16
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2022/day16/
 */
package com.ginsberg.advent2022

import com.github.shiguruikai.combinatoricskt.combinations
import com.github.shiguruikai.combinatoricskt.permutations

class Day16(input: List<String>) {

    private val rooms: Map<String, ValveRoom> = input.map { ValveRoom.of(it) }.associateBy { it.name }
    private val cheapestPathCosts: Map<String, Map<String, Int>> = calculateShortestPaths()

    fun solvePart1(): Int = searchPaths("AA", 30)

    fun solvePart2(): Int =
        cheapestPathCosts.keys.filter { it != "AA" }
            .combinations(cheapestPathCosts.size / 2)
            .map { it.toSet() }
            .maxOf { halfOfTheRooms ->
                searchPaths("AA", 26, halfOfTheRooms) + searchPaths("AA", 26, cheapestPathCosts.keys - halfOfTheRooms)
            }

    private fun searchPaths(
        location: String,
        timeAllowed: Int,
        seen: Set<String> = emptySet(),
        timeTaken: Int = 0,
        totalFlow: Int = 0
    ): Int = cheapestPathCosts
        .getValue(location)
        .asSequence()
        .filterNot { (nextRoom, _) -> nextRoom in seen }
        .filter { (_, traversalCost) -> timeTaken + traversalCost + 1 < timeAllowed }
        .maxOfOrNull { (nextRoom, traversalCost) ->
            searchPaths(
                nextRoom,
                timeAllowed,
                seen + nextRoom,
                timeTaken + traversalCost + 1,
                totalFlow + ((timeAllowed - timeTaken - traversalCost - 1) * rooms.getValue(nextRoom).flowRate)
            )
        } ?: totalFlow


    private fun calculateShortestPaths(): Map<String, Map<String, Int>> {
        val shortestPaths = rooms.values.associate {
            it.name to it.paths.associateWith { 1 }.toMutableMap()
        }.toMutableMap()
        shortestPaths.keys.permutations(3).forEach { (waypoint, from, to) ->
            shortestPaths[from, to] = minOf(
                shortestPaths[from, to], // Existing Path
                shortestPaths[from, waypoint] + shortestPaths[waypoint, to] // New Path
            )
        }
        val zeroFlowRooms = rooms.values.filter { it.flowRate == 0 || it.name == "AA" }.map { it.name }.toSet()
        shortestPaths.values.forEach { it.keys.removeAll(zeroFlowRooms) }
        val canGetToFromAA: Set<String> = shortestPaths.getValue("AA").keys
        return shortestPaths.filter { it.key in canGetToFromAA || it.key == "AA" }
    }

    private operator fun Map<String, MutableMap<String, Int>>.set(key1: String, key2: String, value: Int) {
        getValue(key1)[key2] = value
    }

    private operator fun Map<String, Map<String, Int>>.get(key1: String, key2: String, defaultValue: Int = 31000): Int =
        get(key1)?.get(key2) ?: defaultValue

    private data class ValveRoom(val name: String, val paths: List<String>, val flowRate: Int) {
        companion object {
            fun of(input: String): ValveRoom =
                ValveRoom(
                    input.substringAfter(" ").substringBefore(" "),
                    input.substringAfter("valve").substringAfter(" ").split(", "),
                    input.substringAfter("=").substringBefore(";").toInt()
                )
        }
    }
}
