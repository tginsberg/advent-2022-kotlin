/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

/**
 * Advent of Code 2022, Day 7 - No Space Left On Device
 * Problem Description: http://adventofcode.com/2022/day/7
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2022/day7/
 */
package com.ginsberg.advent2022

class Day07(input: List<String>) {

    private val rootDirectory: Directory = parseInput(input)

    fun solvePart1(): Int =
        rootDirectory.find { it.size <= 100_000 }.sumOf { it.size }

    fun solvePart2(): Int {
        val unusedSpace = 70_000_000 - rootDirectory.size
        val deficit = 30_000_000 - unusedSpace
        return rootDirectory.find { it.size >= deficit }.minBy { it.size }.size
    }

    private fun parseInput(input: List<String>): Directory {
        val callStack = ArrayDeque<Directory>().apply { add(Directory("/")) }
        input.forEach { item ->
            when {
                item == "$ ls" -> {}// Noop
                item.startsWith("dir") -> {} // Noop
                item == "$ cd /" -> callStack.removeIf { it.name != "/" }
                item == "$ cd .." -> callStack.removeFirst()
                item.startsWith("$ cd") -> {
                    val name = item.substringAfterLast(" ")
                    callStack.addFirst(callStack.first().traverse(name))
                }
                else -> {
                    val size = item.substringBefore(" ").toInt()
                    callStack.first().addFile(size)
                }
            }
        }
        return callStack.last()
    }

    class Directory(val name: String) {
        private val subDirs: MutableMap<String, Directory> = mutableMapOf()
        private var sizeOfFiles: Int = 0

        val size: Int
            get() = sizeOfFiles + subDirs.values.sumOf { it.size }

        fun addFile(size: Int) {
            sizeOfFiles += size
        }

        fun traverse(dir: String): Directory =
            subDirs.getOrPut(dir) { Directory(dir) }

        fun find(predicate: (Directory) -> Boolean): List<Directory> =
            subDirs.values.filter(predicate) +
                subDirs.values.flatMap { it.find(predicate) }
    }
}
