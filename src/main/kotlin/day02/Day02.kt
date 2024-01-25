package day02

object Day02 {
    private val limits = mapOf("red" to 12, "green" to 13, "blue" to 14)

    fun processPart1(input: String): Int =
        input.split("\n").sumOf {
            val line = it.split(":")
            val game = line[0].split(" ")[1].toInt()
            val draws = line[1].split(";")
            if (draws.any { draw ->
                    draw.split(",").any { cube ->
                        val (_, number, color) = cube.split(" ")
                        val limit = limits.getOrDefault(color, 15)
                        number.toInt() > limit
                    }
                }) 0 else game
        }

    fun processPart2(input: String): Int =
        input.split("\n").sumOf {
            val line = it.split(":")
            val draws = line[1].split(";")
            val minimumBallsRequired = mutableMapOf("red" to 0, "green" to 0, "blue" to 0)
            val b = draws.forEach() { draw ->
                draw.split(",").forEach() { cube ->
                    val (_, number, color) = cube.split(" ")
                    val max = minimumBallsRequired[color]!!
                    if (number.toInt() > max) {
                        minimumBallsRequired[color] = number.toInt()
                    }
                }
            }
            minimumBallsRequired.values.reduce { acc, current -> acc * current }
        }
}