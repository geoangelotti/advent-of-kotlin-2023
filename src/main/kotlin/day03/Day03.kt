package day03

object Day03 {
    private fun getSymbolLocations(input: String): Set<Pair<Int, Int>> {
        val locations = mutableSetOf<Pair<Int, Int>>()
        val symbolRegex = Regex("[^0-9.]")
        input.lines().forEachIndexed { index, s ->
            symbolRegex.findAll(s).forEach { matchResult ->
                locations.add(Pair(matchResult.range.first, index))
            }
        }
        return locations
    }

    private fun getNumbers(input: String): List<Triple<Int, Int, IntRange>> {
        val numberRegex = Regex("\\d+")
        val numbers = mutableListOf<Triple<Int, Int, IntRange>>()
        input.lines().forEachIndexed { index, s ->
            numberRegex.findAll(s).forEach { matchResult: MatchResult ->
                numbers.add(Triple(matchResult.value.toInt(), index, matchResult.range))
            }
        }
        return numbers
    }

    private fun pointsOfInterest(range: Pair<Int, IntRange>): Set<Pair<Int, Int>> {
        val points = mutableSetOf<Pair<Int, Int>>()
        val j = range.first
        range.second.forEach { i ->
            points.add(i - 1 to j - 1)
            points.add(i - 1 to j)
            points.add(i - 1 to j + 1)
            points.add(i to j - 1)
            points.add(i to j + 1)
            points.add(i + 1 to j - 1)
            points.add(i + 1 to j)
            points.add(i + 1 to j + 1)
        }
        return points
    }

    fun processPart1(input: String): Int {
        val symbolLocations = getSymbolLocations(input)
        val numbers = getNumbers(input)
        return numbers
            .filter { pointsOfInterest(it.second to it.third).any { point -> symbolLocations.contains(point) } }
            .sumOf { it.first }
    }

    fun processPart2(input: String): Int {
        TODO()
    }
}