package com.ginsberg.advent2022

import kotlin.math.absoluteValue
import kotlin.math.sign

data class Point2D(val x: Int = 0, val y: Int = 0) {
    fun cardinalNeighbors(): Set<Point2D> =
        setOf(
            copy(x = x - 1),
            copy(x = x + 1),
            copy(y = y - 1),
            copy(y = y + 1)
        )

    fun neighbors(): Set<Point2D> =
        setOf(
            Point2D(x - 1, y - 1),
            Point2D(x, y - 1),
            Point2D(x + 1, y - 1),
            Point2D(x - 1, y),
            Point2D(x + 1, y),
            Point2D(x - 1, y + 1),
            Point2D(x, y + 1),
            Point2D(x + 1, y + 1)
        )

    operator fun plus(other: Point2D): Point2D =
        Point2D(this.x + other.x, this.y + other.y)

    fun distanceTo(other: Point2D): Int =
        (x - other.x).absoluteValue + (y - other.y).absoluteValue

    fun lineTo(other: Point2D): List<Point2D> {
        val xDelta = (other.x - x).sign
        val yDelta = (other.y - y).sign
        val steps = maxOf((x - other.x).absoluteValue, (y - other.y).absoluteValue)
        return (1..steps).scan(this) { last, _ -> Point2D(last.x + xDelta, last.y + yDelta) }
    }

    companion object {
        fun of(input: String): Point2D =
            input.split(",").let { (x, y) -> Point2D(x.toInt(), y.toInt()) }
    }
}