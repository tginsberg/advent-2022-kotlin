/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

/**
 * Advent of Code 2022, Day 5 - Supply Stacks
 * Problem Description: http://adventofcode.com/2022/day/5
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2022/day5/
 */
package com.ginsberg.advent2022

class Day05(input: List<String>) {

    private val stacks: List<MutableList<Char>> = createStacks(input)
    private val instructions: List<Instruction> = parseInstructions(input)

    fun solvePart1(): String {
        performInstructions(true)
        return stacks.tops()
    }

    fun solvePart2(): String {
        performInstructions(false)
        return stacks.tops()
    }

    private fun performInstructions(reverse: Boolean) {
        instructions.forEach { (amount, source, destination) ->
            val toBeMoved = stacks[source].take(amount)
            repeat(amount) { stacks[source].removeFirst() }
            stacks[destination].addAll(0, if (reverse) toBeMoved.reversed() else toBeMoved)
        }
    }

    private fun Iterable<Iterable<Char>>.tops(): String =
        map { it.first() }.joinToString("")

    private fun createStacks(input: List<String>): List<MutableList<Char>> {
        val stackRows = input.takeWhile { it.contains('[') }
        return (1..stackRows.last().length step 4).map { index ->
            stackRows
                .mapNotNull { it.getOrNull(index) }
                .filter { it.isUpperCase() }
                .toMutableList()
        }
    }

    private fun parseInstructions(input: List<String>): List<Instruction> =
        input
            .dropWhile { !it.startsWith("move") }
            .map { row ->
                row.split(" ").let { parts ->
                    Instruction(parts[1].toInt(), parts[3].toInt() - 1, parts[5].toInt() - 1)
                }
            }

    private data class Instruction(val amount: Int, val source: Int, val target: Int)

}
