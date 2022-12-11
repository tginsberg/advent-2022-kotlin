/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

/**
 * Advent of Code 2022, Day 11 - Monkey in the Middle
 * Problem Description: http://adventofcode.com/2022/day/11
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2022/day11/
 */
package com.ginsberg.advent2022

class Day11(input: List<String>) {

    private val monkeys: List<Monkey> = input.chunked(7).map { Monkey.of(it) }

    fun solvePart1(): Long {
        rounds(20) { it / 3 }
        return monkeys.business()
    }

    fun solvePart2(): Long {
        val testProduct: Long = monkeys.map { it.test }.reduce(Long::times)
        rounds(10_000) { it % testProduct }
        return monkeys.business()
    }

    private fun List<Monkey>.business(): Long =
        sortedByDescending { it.interactions }.let { it[0].interactions * it[1].interactions }

    private fun rounds(numRounds: Int, changeToWorryLevel: (Long) -> Long) {
        repeat(numRounds) {
            monkeys.forEach { it.inspectItems(monkeys, changeToWorryLevel) }
        }
    }

    private class Monkey(
        val items: MutableList<Long>,
        val operation: (Long) -> Long,
        val test: Long,
        val trueMonkey: Int,
        val falseMonkey: Int
    ) {

        var interactions: Long = 0

        fun inspectItems(monkeys: List<Monkey>, changeToWorryLevel: (Long) -> Long) {
            items.forEach { item ->
                val worry = changeToWorryLevel(operation(item))
                val target = if (worry % test == 0L) trueMonkey else falseMonkey
                monkeys[target].items.add(worry)
            }
            interactions += items.size
            items.clear()
        }

        companion object {
            fun of(input: List<String>): Monkey {
                val items = input[1].substringAfter(": ").split(", ").map { it.toLong() }.toMutableList()
                val operationValue = input[2].substringAfterLast(" ")
                val operation: (Long) -> Long = when {
                    operationValue == "old" -> ({ it * it })
                    '*' in input[2] -> ({ it * operationValue.toLong() })
                    else -> ({ it + operationValue.toLong() })
                }
                val test = input[3].substringAfterLast(" ").toLong()
                val trueMonkey = input[4].substringAfterLast(" ").toInt()
                val falseMonkey = input[5].substringAfterLast(" ").toInt()
                return Monkey(
                    items,
                    operation,
                    test,
                    trueMonkey,
                    falseMonkey
                )
            }
        }
    }

}
