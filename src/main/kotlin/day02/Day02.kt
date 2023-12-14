package day02

object Day02 {
    fun processPart1(input: String): Int =
        input.split("\n").sumOf { line ->
            val limits = mapOf("red" to 12, "green" to 13, "blue" to 14)
            val lineSplit = line.split(":")
            val game = lineSplit[0].split(" ")[1].toInt()
            val draws = lineSplit[1].split(";")
            val impossibleGame = draws.map { draw ->
                val cubes = draw.split(",")
                val impossibleDraws = cubes.map{ cube->
                    val (_, number, color) = cube.split(" ")
                    val limit = limits.getOrDefault(color, 15)
                    number.toInt() > limit
                }
                impossibleDraws.any{ it }
            }.any{ it }
            if (impossibleGame) 0 else game
        }
}