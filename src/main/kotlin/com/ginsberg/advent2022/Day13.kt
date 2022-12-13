/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

/**
 * Advent of Code 2022, Day 13 - Distress Signal
 * Problem Description: http://adventofcode.com/2022/day/13
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2022/day13/
 */
package com.ginsberg.advent2022

class Day13(input: List<String>) {

    private val packets = input.filter { it.isNotBlank() }.map { Packet.of(it) }

    fun solvePart1(): Int =
        packets.chunked(2).mapIndexed { index, pair ->
            if (pair.first() < pair.last()) index + 1 else 0
        }.sum()

    fun solvePart2(): Int {
        val dividerPacket1 = Packet.of("[[2]]")
        val dividerPacket2 = Packet.of("[[6]]")
        val ordered = (packets + dividerPacket1 + dividerPacket2).sorted()
        return (ordered.indexOf(dividerPacket1) + 1) * (ordered.indexOf(dividerPacket2) + 1)
    }

    private sealed class Packet : Comparable<Packet> {
        companion object {
            fun of(input: String): Packet =
                of(
                    input.split("""((?<=[\[\],])|(?=[\[\],]))""".toRegex())
                        .filter { it.isNotBlank() }
                        .filter { it != "," }
                        .iterator()
                )

            private fun of(input: Iterator<String>): Packet {
                val packets = mutableListOf<Packet>()
                while (input.hasNext()) {
                    when (val symbol = input.next()) {
                        "]" -> return ListPacket(packets)
                        "[" -> packets.add(of(input))
                        else -> packets.add(IntPacket(symbol.toInt()))
                    }
                }
                return ListPacket(packets)
            }
        }
    }

    private class IntPacket(val amount: Int) : Packet() {
        fun asList(): Packet = ListPacket(listOf(this))

        override fun compareTo(other: Packet): Int =
            when (other) {
                is IntPacket -> amount.compareTo(other.amount)
                is ListPacket -> asList().compareTo(other)
            }
    }

    private class ListPacket(val subPackets: List<Packet>) : Packet() {
        override fun compareTo(other: Packet): Int =
            when (other) {
                is IntPacket -> compareTo(other.asList())
                is ListPacket -> subPackets.zip(other.subPackets)
                    .map { it.first.compareTo(it.second) }
                    .firstOrNull { it != 0 } ?: subPackets.size.compareTo(other.subPackets.size)
            }
    }
}
