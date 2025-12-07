package day06

object Day06 {
    private fun parseInput(input: String): List<Pair<Int, Int>> {
        val lines = input.lines()
        val extract = { line: String ->
            line.substringAfter(":").trim().split("\\s+".toRegex()).map { it.toInt() } }
        val times = extract(lines[0])
        val distances = extract(lines[1])
        return times.zip(distances)
    }

    fun processPart1(input: String): Int {
        val zipped = parseInput(input)
        return zipped.map { (time, distance) ->
            val minimumTimeHeld = 0.until(time + 1).find { timeHeld -> timeHeld * (time - timeHeld) > distance }
            time - 2 * minimumTimeHeld!! + 1
        }.reduce { acc, i -> acc * i }
    }
}