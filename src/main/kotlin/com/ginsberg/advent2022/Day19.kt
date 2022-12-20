/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

/**
 * Advent of Code 2022, Day 19 - Not Enough Minerals
 * Problem Description: http://adventofcode.com/2022/day/19
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2022/day19/
 */
package com.ginsberg.advent2022

import java.util.PriorityQueue
import kotlin.math.ceil

class Day19(input: List<String>) {

    private val blueprints: List<Blueprint> = input.map { Blueprint.of(it) }

    fun solvePart1(): Int =
        blueprints.sumOf { it.id * calculateGeodesFound(it, 24) }

    fun solvePart2(): Int =
        blueprints.take(3).map { calculateGeodesFound(it, 32) }.reduce(Int::times)

    private fun calculateGeodesFound(blueprint: Blueprint, timeBudget: Int): Int {
        var maxGeodes = 0
        val queue = PriorityQueue<ProductionState>().apply { add(ProductionState()) }

        while(queue.isNotEmpty()){
            val state = queue.poll()
            if (state.canOutproduceBest(maxGeodes, timeBudget)) {
                queue.addAll(state.calculateNextStates(blueprint, timeBudget))
            }
            maxGeodes = maxOf(maxGeodes, state.geodes)
        }

        return maxGeodes
    }

    private data class ProductionState(
        val time: Int = 1,
        val ore: Int = 1,
        val oreRobots: Int = 1,
        val clay: Int = 0,
        val clayRobots: Int = 0,
        val obsidian: Int = 0,
        val obsidianRobots: Int = 0,
        val geodes: Int = 0,
        val geodeRobots: Int = 0
    ): Comparable<ProductionState> {

        override fun compareTo(other: ProductionState): Int = other.geodes.compareTo(geodes)

        fun canOutproduceBest(bestSoFar: Int, timeBudget: Int): Boolean {
            val timeLeft = timeBudget - time
            val potentialProduction = (0 until timeLeft).sumOf { it + geodeRobots }
            return geodes + potentialProduction > bestSoFar
        }

        fun calculateNextStates(blueprint: Blueprint, timeBudget: Int): List<ProductionState> {
            val nextStates = mutableListOf<ProductionState>()
            if (time < timeBudget) {
                if (blueprint.maxOre > oreRobots && ore > 0) {
                    nextStates += blueprint.oreRobot.scheduleBuild(this)
                }
                if (blueprint.maxClay > clayRobots && ore > 0) {
                    nextStates += blueprint.clayRobot.scheduleBuild(this)
                }
                if (blueprint.maxObsidian > obsidianRobots && ore > 0 && clay > 0) {
                    nextStates += blueprint.obsidianRobot.scheduleBuild(this)
                }
                if (ore > 0 && obsidian > 0) {
                    nextStates += blueprint.geodeRobot.scheduleBuild(this)
                }
            }
            return nextStates.filter { it.time <= timeBudget }
        }
    }

    private data class RobotBlueprint(
        val oreRobotsBuilt: Int,
        val clayRobotsBuilt: Int,
        val obsidianRobotsBuilt: Int,
        val geodeRobotsBuilt: Int,
        val oreCost: Int,
        val clayCost: Int = 0,
        val obsidianCost: Int = 0
    ) {
        private fun timeUntilBuild(productionState: ProductionState): Int =
            maxOf(
                if (oreCost <= productionState.ore) 0 else ceil((oreCost - productionState.ore) / productionState.oreRobots.toFloat()).toInt(),
                if (clayCost <= productionState.clay) 0 else ceil((clayCost - productionState.clay) / productionState.clayRobots.toFloat()).toInt(),
                if (obsidianCost <= productionState.obsidian) 0 else ceil((obsidianCost - productionState.obsidian) / productionState.obsidianRobots.toFloat()).toInt()
            ) + 1

        fun scheduleBuild(state: ProductionState): ProductionState {
            val timeRequired = timeUntilBuild(state)
            return state.copy(
                time = state.time + timeRequired,
                ore = (state.ore - oreCost) + (timeRequired * state.oreRobots),
                oreRobots = state.oreRobots + oreRobotsBuilt,
                clay = (state.clay - clayCost) + (timeRequired * state.clayRobots),
                clayRobots = state.clayRobots + clayRobotsBuilt,
                obsidian = (state.obsidian - obsidianCost) + (timeRequired * state.obsidianRobots),
                obsidianRobots = state.obsidianRobots + obsidianRobotsBuilt,
                geodes = state.geodes + (timeRequired * state.geodeRobots),
                geodeRobots = state.geodeRobots + geodeRobotsBuilt
            )
        }
    }

    private data class Blueprint(
        val id: Int,
        val oreRobot: RobotBlueprint,
        val clayRobot: RobotBlueprint,
        val obsidianRobot: RobotBlueprint,
        val geodeRobot: RobotBlueprint
    ) {
        val maxOre: Int =
            maxOf(oreRobot.oreCost, clayRobot.oreCost, obsidianRobot.oreCost, geodeRobot.oreCost)

        val maxClay: Int =
            maxOf(oreRobot.clayCost, clayRobot.clayCost, obsidianRobot.clayCost, geodeRobot.clayCost)

        val maxObsidian: Int =
            maxOf(oreRobot.obsidianCost, clayRobot.obsidianCost, obsidianRobot.obsidianCost, geodeRobot.obsidianCost)

        companion object {
            fun of(input: String): Blueprint {
                val parts = input.split(" ", ": ")
                return Blueprint(
                    id = parts[1].toInt(),
                    oreRobot = RobotBlueprint(1, 0, 0, 0, parts[6].toInt()),
                    clayRobot = RobotBlueprint(0, 1, 0, 0, parts[12].toInt()),
                    obsidianRobot = RobotBlueprint(0, 0, 1, 0, parts[18].toInt(), parts[21].toInt()),
                    geodeRobot = RobotBlueprint(0, 0, 0, 1, parts[27].toInt(), 0, parts[30].toInt()),
                )
            }
        }
    }
}
