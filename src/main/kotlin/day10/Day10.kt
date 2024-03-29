package day10

import java.util.PriorityQueue

object Day10 {
    private fun potentialNeighbours(
        point: Pair<Int, Int>,
        grid: MutableList<MutableList<PipePart>>
    ): List<Pair<Int, Int>> =
        when (grid[point.second][point.first]) {
            PipePart.NORTHSOUTH -> listOf(
                Pair(point.first, point.second - 1),
                Pair(point.first, point.second + 1)
            )

            PipePart.WESTEAST -> listOf(
                Pair(point.first - 1, point.second),
                Pair(point.first + 1, point.second),
            )

            PipePart.NORTHEAST -> listOf(
                Pair(point.first, point.second - 1),
                Pair(point.first + 1, point.second),
            )

            PipePart.NORTHWEST -> listOf(
                Pair(point.first, point.second - 1),
                Pair(point.first - 1, point.second),
            )

            PipePart.SOUTHEAST -> listOf(
                Pair(point.first, point.second + 1),
                Pair(point.first + 1, point.second),
            )

            PipePart.SOUTHWEST -> listOf(
                Pair(point.first, point.second + 1),
                Pair(point.first - 1, point.second),
            )

            PipePart.GROUND -> listOf()
            PipePart.START -> listOf(
                Pair(point.first, point.second - 1),
                Pair(point.first, point.second + 1),
                Pair(point.first - 1, point.second),
                Pair(point.first + 1, point.second),
            )
        }

    private fun charToPipe(char: Char): PipePart =
        when (char) {
            'S' -> PipePart.START
            '|' -> PipePart.NORTHSOUTH
            '-' -> PipePart.WESTEAST
            'L' -> PipePart.NORTHEAST
            'J' -> PipePart.NORTHWEST
            'F' -> PipePart.SOUTHEAST
            '7' -> PipePart.SOUTHWEST
            else -> PipePart.GROUND
        }

    private fun getGrid(input: String): Pair<MutableList<MutableList<PipePart>>, Pair<Int, Int>?> {
        val grid = mutableListOf<MutableList<PipePart>>()
        var start: Pair<Int, Int>? = null
        input.lines().withIndex().forEach { line ->
            val row = mutableListOf<PipePart>()
            line.value.withIndex().forEach { column ->
                val part = charToPipe(column.value)
                if (part == PipePart.START)
                    start = Pair(column.index, line.index)
                row.add(part)
            }
            grid.add(row)
        }
        return Pair(grid, start)
    }

    private fun getEdges(grid: MutableList<MutableList<PipePart>>): List<Pair<Pair<Int, Int>, Pair<Int, Int>>> =
        grid.withIndex().map { row ->
            row.value.withIndex().map { column ->
                val parent = Pair(column.index, row.index)
                potentialNeighbours(parent, grid).mapNotNull { neighbour ->
                    try {
                        val commonNeighbours = potentialNeighbours(neighbour, grid).toSet()
                            .intersect(setOf(parent))
                        commonNeighbours.ifEmpty { null }?.map { Pair(it, neighbour) }
                    } catch (e: IndexOutOfBoundsException) {
                        null
                    }
                }.flatten()
            }.flatten()
        }.flatten()

    private fun djikstra(start: Int, edges: List<List<Int>>): IntArray {
        val n = edges.size
        val dist = IntArray(n) { Int.MAX_VALUE }
        dist[start] = 0

        val priorityQueue = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
        priorityQueue.add(Pair(start, 0))
        while (priorityQueue.isNotEmpty()) {
            val (u, uDist) = priorityQueue.poll()
            if (uDist > dist[u])
                continue
            for (edge in edges[u]) {
                val weight = 1
                if (uDist + weight < dist[edge]) {
                    dist[edge] = uDist + weight
                    priorityQueue.add(Pair(edge, dist[edge]))
                }
            }
        }
        return dist
    }

    private fun filterNext(
        point: Pair<Int, Int>,
        grid: MutableList<MutableList<PipePart>>,
        seen: Set<Pair<Int, Int>>
    ): Pair<Int, Int>? {
        try {
            val neighbours = potentialNeighbours(point, grid)
            val commonNeighbours = neighbours.toSet().intersect(seen).toList()
            return commonNeighbours.ifEmpty { null }?.let { point }
        } catch (e: IndexOutOfBoundsException) {
            return null
        }
    }

    private fun traverse(input: String): Int {
        val (grid, start) = getGrid(input)
        val seen = mutableSetOf(start!!)
        var next = potentialNeighbours(start, grid)
        var acc = 0
        while (!next.all { seen.contains(it) }) {
            next.forEach { seen.add(it) }
            acc += 1
            val filtered = next.mapNotNull { neighbour -> filterNext(neighbour, grid, seen) }
            next = filtered.map { potentialNeighbours(it, grid) }.flatten().filter { !seen.contains(it) }
        }
        return acc
    }

    private fun djikstraSolution(input: String): Int {
        val (grid, start) = getGrid(input)
        val edges = getEdges(grid)
        val vertices = edges.map { it.first }.toSet()
            .sortedWith { a, b -> if (a.first == b.first) a.second - b.second else a.first - b.first }
        val verticesMap = vertices.withIndex().associate { Pair(it.value, it.index) }
        val simplerEdges = edges.map { verticesMap[it.first]!! to verticesMap[it.second]!! }
        val graphEdges = List(vertices.size) { mutableListOf<Int>() }
        val startingVertex = verticesMap[start]!!
        simplerEdges.forEach { graphEdges[it.first].add(it.second) }
        val result = djikstra(startingVertex, graphEdges)
        return result.toList().filter { it < Int.MAX_VALUE }.maxOf { it }
    }

    fun processPart1(input: String): Int {
        return traverse(input)
    }
}
