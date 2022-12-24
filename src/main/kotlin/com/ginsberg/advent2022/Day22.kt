/*
 * Copyright (c) 2022 by Todd Ginsberg
 */

/**
 * Advent of Code 2022, Day 22 - Monkey Map
 * Problem Description: http://adventofcode.com/2022/day/22
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2022/day22/
 */
package com.ginsberg.advent2022

class Day22(input: List<String>) {

    private val blockedPlaces: Set<Point2D> = parseBlockedPlaces(input)
    private val instructions: List<Instruction> = Instruction.ofList(input)

    fun solvePart1(): Int =
        followInstructions(CubeFacing(1)).score()

    fun solvePart2(): Int =
        followInstructions(CubeFacing(11)).score()

    private fun followInstructions(startingCube: CubeFacing): Orientation {
        var cube = startingCube
        var orientation = Orientation(cube.topLeft, Facing.East)

        instructions.forEach { instruction ->
            when (instruction) {
                is Left -> orientation = orientation.copy(facing = orientation.facing.left)
                is Right -> orientation = orientation.copy(facing = orientation.facing.right)
                is Move -> {
                    var keepMoving = true
                    repeat(instruction.steps) {
                        if (keepMoving) {
                            var nextOrientation = orientation.move()
                            var nextCube = cube

                            if (nextOrientation.location !in cube) {
                                // We have moved off the face of the cube we were on, find the transition we need.
                                with(cube.transition(orientation.facing)) {
                                    nextOrientation = Orientation(move(orientation.location), enter)
                                    nextCube = destination
                                }
                            }
                            if (nextOrientation.location in blockedPlaces) {
                                // We cannot move to this spot - it is on the map but blocking us.
                                keepMoving = false
                            } else {
                                // We can move to this spot - it is on the map AND free
                                orientation = nextOrientation
                                cube = nextCube
                            }
                        }
                    }
                }
            }
        }
        return orientation
    }

    private fun parseBlockedPlaces(input: List<String>): Set<Point2D> =
        input
            .takeWhile { it.isNotBlank() }
            .flatMapIndexed { y, row ->
                row.mapIndexedNotNull { x, c ->
                    if (c == '#') Point2D(x, y) else null
                }
            }.toSet()

    private data class Orientation(val location: Point2D, val facing: Facing) {
        fun score(): Int =
            (1000 * (location.y + 1)) + (4 * (location.x + 1)) + facing.points

        fun move(): Orientation =
            copy(location = location + facing.offset)
    }

    private sealed class Instruction {
        companion object {
            fun ofList(input: List<String>): List<Instruction> =
                input
                    .dropWhile { it.isNotBlank() }
                    .drop(1)
                    .first()
                    .trim()
                    .let { """\d+|[LR]""".toRegex().findAll(it) }
                    .map { it.value }
                    .filter { it.isNotBlank() }
                    .map { symbol ->
                        when (symbol) {
                            "L" -> Left
                            "R" -> Right
                            else -> Move(symbol.toInt())
                        }
                    }.toList()
        }
    }

    private class Move(val steps: Int) : Instruction()
    private object Left : Instruction()
    private object Right : Instruction()

    sealed class Facing(val points: Int, val offset: Point2D) {
        abstract val left: Facing
        abstract val right: Facing

        object North : Facing(3, Point2D(0, -1)) {
            override val left = West
            override val right = East
        }

        object East : Facing(0, Point2D(1, 0)) {
            override val left = North
            override val right = South
        }

        object South : Facing(1, Point2D(0, 1)) {
            override val left = East
            override val right = West
        }

        object West : Facing(2, Point2D(-1, 0)) {
            override val left = South
            override val right = North
        }
    }

    class CubeFacing(
        val id: Int,
        val size: Int,
        val topLeft: Point2D,
        val north: Transition,
        val east: Transition,
        val south: Transition,
        val west: Transition
    ) {
        val minX: Int = topLeft.x
        val maxX: Int = topLeft.x + size - 1
        val minY: Int = topLeft.y
        val maxY: Int = topLeft.y + size - 1

        operator fun contains(place: Point2D): Boolean =
            place.x in (minX..maxX) && place.y in (minY..maxY)

        fun scaleDown(point: Point2D): Point2D =
            point - topLeft

        fun scaleUp(point: Point2D): Point2D =
            point + topLeft

        fun transition(direction: Facing): Transition =
            when (direction) {
                Facing.North -> north
                Facing.East -> east
                Facing.South -> south
                Facing.West -> west
            }

        companion object {
            private val instances = mutableMapOf<Int, CubeFacing>()

            operator fun invoke(id: Int): CubeFacing =
                instances.getValue(id)

            private operator fun plus(instance: CubeFacing) {
                instances[instance.id] = instance
            }

            init {
                CubeFacing + CubeFacing(
                    id = 1,
                    size = 50,
                    topLeft = Point2D(50, 0),
                    north = Transition(1, 5, Facing.North, Facing.North),
                    east = Transition(1, 2, Facing.East, Facing.East),
                    south = Transition(1, 3, Facing.South, Facing.South),
                    west = Transition(1, 2, Facing.West, Facing.West)
                )

                CubeFacing + CubeFacing(
                    id = 2,
                    size = 50,
                    topLeft = Point2D(100, 0),
                    north = Transition(2, 2, Facing.North, Facing.North),
                    east = Transition(2, 1, Facing.East, Facing.East),
                    south = Transition(2, 2, Facing.South, Facing.South),
                    west = Transition(2, 1, Facing.West, Facing.West)
                )

                CubeFacing + CubeFacing(
                    id = 3,
                    size = 50,
                    topLeft = Point2D(50, 50),
                    north = Transition(3, 1, Facing.North, Facing.North),
                    east = Transition(3, 3, Facing.East, Facing.East),
                    south = Transition(3, 5, Facing.South, Facing.South),
                    west = Transition(3, 3, Facing.West, Facing.West)
                )

                CubeFacing + CubeFacing(
                    id = 4,
                    size = 50,
                    topLeft = Point2D(0, 100),
                    north = Transition(4, 6, Facing.North, Facing.North),
                    east = Transition(4, 5, Facing.East, Facing.East),
                    south = Transition(4, 6, Facing.South, Facing.South),
                    west = Transition(4, 5, Facing.West, Facing.West)
                )

                CubeFacing + CubeFacing(
                    id = 5,
                    size = 50,
                    topLeft = Point2D(50, 100),
                    north = Transition(5, 3, Facing.North, Facing.North),
                    east = Transition(5, 4, Facing.East, Facing.East),
                    south = Transition(5, 1, Facing.South, Facing.South),
                    west = Transition(5, 4, Facing.West, Facing.West)
                )

                CubeFacing + CubeFacing(
                    id = 6,
                    size = 50,
                    topLeft = Point2D(0, 150),
                    north = Transition(6, 4, Facing.North, Facing.North),
                    east = Transition(6, 6, Facing.East, Facing.East),
                    south = Transition(6, 4, Facing.South, Facing.South),
                    west = Transition(6, 6, Facing.West, Facing.West)
                )

                CubeFacing + CubeFacing(
                    id = 11,
                    size = 50,
                    topLeft = Point2D(50, 0),
                    north = Transition(11, 16, Facing.North, Facing.East),
                    east = Transition(11, 12, Facing.East, Facing.East),
                    south = Transition(11, 13, Facing.South, Facing.South),
                    west = Transition(11, 14, Facing.West, Facing.East)
                )
                CubeFacing + CubeFacing(
                    id = 12,
                    size = 50,
                    topLeft = Point2D(100, 0),
                    north = Transition(12, 16, Facing.North, Facing.North),
                    east = Transition(12, 15, Facing.East, Facing.West),
                    south = Transition(12, 13, Facing.South, Facing.West),
                    west = Transition(12, 11, Facing.West, Facing.West)
                )

                CubeFacing + CubeFacing(
                    id = 13,
                    size = 50,
                    topLeft = Point2D(50, 50),
                    north = Transition(13, 11, Facing.North, Facing.North),
                    east = Transition(13, 12, Facing.East, Facing.North),
                    south = Transition(13, 15, Facing.South, Facing.South),
                    west = Transition(13, 14, Facing.West, Facing.South)
                )

                CubeFacing + CubeFacing(
                    id = 14,
                    size = 50,
                    topLeft = Point2D(0, 100),
                    north = Transition(14, 13, Facing.North, Facing.East),
                    east = Transition(14, 15, Facing.East, Facing.East),
                    south = Transition(14, 16, Facing.South, Facing.South),
                    west = Transition(14, 11, Facing.West, Facing.East)
                )

                CubeFacing + CubeFacing(
                    id = 15,
                    size = 50,
                    topLeft = Point2D(50, 100),
                    north = Transition(15, 13, Facing.North, Facing.North),
                    east = Transition(15, 12, Facing.East, Facing.West),
                    south = Transition(15, 16, Facing.South, Facing.West),
                    west = Transition(15, 14, Facing.West, Facing.West)
                )

                CubeFacing + CubeFacing(
                    id = 16,
                    size = 50,
                    topLeft = Point2D(0, 150),
                    north = Transition(16, 14, Facing.North, Facing.North),
                    east = Transition(16, 15, Facing.East, Facing.North),
                    south = Transition(16, 12, Facing.South, Facing.South),
                    west = Transition(16, 11, Facing.West, Facing.South)
                )
            }
        }
    }

    data class Transition(val sourceId: Int, val destinationId: Int, val exit: Facing, val enter: Facing) {
        private val byDirection = Pair(exit, enter)
        private val source: CubeFacing by lazy { CubeFacing(sourceId) }
        val destination: CubeFacing by lazy { CubeFacing(destinationId) }

        private fun Point2D.rescale(): Point2D =
            destination.scaleUp(source.scaleDown(this))

        private fun Point2D.flipRescaled(): Point2D =
            destination.scaleUp(source.scaleDown(this).flip())

        private fun Point2D.flip(): Point2D =
            Point2D(y, x)

        fun move(start: Point2D): Point2D = when (byDirection) {
            Pair(Facing.North, Facing.North) -> Point2D(start.rescale().x, destination.maxY)
            Pair(Facing.East, Facing.East) -> Point2D(destination.minX, start.rescale().y)
            Pair(Facing.West, Facing.West) -> Point2D(destination.maxX, start.rescale().y)
            Pair(Facing.South, Facing.South) -> Point2D(start.rescale().x, destination.minY)

            Pair(Facing.North, Facing.East) -> Point2D(destination.minX, start.flipRescaled().y)
            Pair(Facing.East, Facing.North) -> Point2D(start.flipRescaled().x, destination.maxY)
            Pair(Facing.East, Facing.West) -> Point2D(destination.maxX, destination.maxY - source.scaleDown(start).y)
            Pair(Facing.West, Facing.East) -> Point2D(destination.minX, destination.maxY - source.scaleDown(start).y)
            Pair(Facing.West, Facing.South) -> Point2D(start.flipRescaled().x, destination.minY)
            Pair(Facing.South, Facing.West) -> Point2D(destination.maxX, start.flipRescaled().y)
            else -> throw IllegalStateException("No transition from $exit to $enter")
        }
    }
}