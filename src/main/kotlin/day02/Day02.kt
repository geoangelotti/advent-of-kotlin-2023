package day02

object Day02 {
    private val limits = mapOf("red" to 12, "green" to 13, "blue" to 14)

    fun processPart1(input: String): Int =
        input.split("\n").sumOf {
            val line = it.split(":")
            val game = line[0].split(" ")[1].toInt()
            val draws = line[1].split(";")
            if (draws.any { draw ->
                draw.split(",").any{ cube->
                    val (_, number, color) = cube.split(" ")
                    val limit = limits.getOrDefault(color, 15)
                    number.toInt() > limit
                }
            }) 0 else game
        }
}