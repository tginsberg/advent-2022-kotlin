/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

/**
 * Advent of Code 2022, Day 21 - Monkey Math
 * Problem Description: http://adventofcode.com/2022/day/21
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2022/day21/
 */
package com.ginsberg.advent2022

class Day21(input: List<String>) {

    private val monkeys: Set<Monkey> = parseInput(input)
    private val root: Monkey = monkeys.first { it.name == "root" }

    fun solvePart1(): Long =
        root.yell()

    fun solvePart2(): Long =
        root.calculateHumanValue()

    private fun parseInput(input: List<String>): Set<Monkey> {
        val monkeyCache: Map<String, Monkey> = input.map { Monkey(it) }.associateBy { it.name }
        monkeyCache.values.filterIsInstance<FormulaMonkey>().forEach { monkey ->
            monkey.left = monkeyCache.getValue(monkey.leftName)
            monkey.right = monkeyCache.getValue(monkey.rightName)
        }
        return monkeyCache.values.toSet()
    }

    private interface Monkey {
        val name: String
        fun yell(): Long
        fun findHumanPath(): Set<Monkey>
        fun calculateHumanValue(humanPath: Set<Monkey> = findHumanPath(), incoming: Long = 0L): Long

        companion object {
            operator fun invoke(row: String): Monkey {
                val name = row.substringBefore(":")
                return if (row.length == 17) {
                    FormulaMonkey(name, row.substring(6..9), row.substringAfterLast(" "), row[11])
                } else {
                    NumberMonkey(name, row.substringAfter(" ").toLong())
                }
            }
        }
    }

    private class NumberMonkey(
        override val name: String,
        val number: Long
    ) : Monkey {
        override fun yell(): Long = number

        override fun findHumanPath(): Set<Monkey> =
            if (name == "humn") setOf(this) else emptySet()

        override fun calculateHumanValue(humanPath: Set<Monkey>, incoming: Long): Long =
            if (name == "humn") incoming else number
    }

    private class FormulaMonkey(
        override val name: String,
        val leftName: String,
        val rightName: String,
        val op: Char
    ) : Monkey {
        lateinit var left: Monkey
        lateinit var right: Monkey

        override fun calculateHumanValue(humanPath: Set<Monkey>, incoming: Long): Long =
            if (name == "root") {
                if (left in humanPath) left.calculateHumanValue(humanPath, right.yell())
                else right.calculateHumanValue(humanPath, left.yell())
            } else if (left in humanPath) {
                left.calculateHumanValue(humanPath, incoming leftAntiOp right.yell()) // Negate
            } else {
                right.calculateHumanValue(humanPath, incoming rightAntiOp left.yell()) // Negate
            }

        override fun findHumanPath(): Set<Monkey> {
            val leftPath = left.findHumanPath()
            val rightPath = right.findHumanPath()
            return if (leftPath.isNotEmpty()) leftPath + this
            else if (rightPath.isNotEmpty()) rightPath + this
            else emptySet()
        }

        override fun yell(): Long =
            left.yell() op right.yell()

        private infix fun Long.op(right: Long): Long =
            when (op) {
                '+' -> this + right
                '-' -> this - right
                '*' -> this * right
                else -> this / right
            }

        private infix fun Long.leftAntiOp(right: Long): Long =
            when (op) {
                '+' -> this - right
                '-' -> this + right
                '*' -> this / right
                else -> this * right
            }

        private infix fun Long.rightAntiOp(right: Long): Long =
            when (op) {
                '+' -> this - right
                '-' -> right - this
                '*' -> this / right
                else -> right / this
            }
    }
}
