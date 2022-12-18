package com.ginsberg.advent2022

data class Point3D(val x: Int = 0, val y: Int = 0, val z: Int = 0) {
    fun cardinalNeighbors(): Set<Point3D> =
        setOf(
            copy(x = x - 1),
            copy(x = x + 1),
            copy(y = y - 1),
            copy(y = y + 1),
            copy(z = z - 1),
            copy(z = z + 1)
        )

    companion object {
        fun of(input: String): Point3D =
            input.split(",").let { (x, y, z) -> Point3D(x.toInt(), y.toInt(), z.toInt()) }
    }
}