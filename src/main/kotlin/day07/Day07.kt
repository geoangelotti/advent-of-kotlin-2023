package day07

object Day07 {

    private fun process(input: String, strengthResolver: (String) -> Pair<HandType, List<Int>>): Int =
        input.lines().map { line ->
            val (cards, bid) = line.split(" ")
            Triple(cards, bid.toInt(), strengthResolver(cards))
        }.sortedWith { (_, _, a), (_, _, b) ->
            if (a.first != b.first) {
                return@sortedWith a.first.ordinal - b.first.ordinal
            }
            a.second.zip(b.second).fold(0) { acc, (a2, b2) ->
                if (a2 != b2) {
                    return@sortedWith a2 - b2
                }
                acc
            }
        }.withIndex().sumOf { (it.index + 1) * it.value.second }

    fun processPart1(input: String): Int =
        process(input, ::getHandStrength)

    fun processPart2(input: String): Int =
        0

    private fun getHandStrength(cards: String): Pair<HandType, List<Int>> {
        val count = cards.fold(mutableMapOf<Char, Int>()) { acc, c ->
            acc.putIfAbsent(c, 0)
            acc[c] = acc[c]!! + 1
            acc
        }.values.sorted().joinToString("")
        val handType = when (count) {
            "5" -> HandType.FiveOfAKind
            "14" -> HandType.FourOfAKind
            "23" -> HandType.FullHouse
            "113" -> HandType.ThreeOfAKind
            "122" -> HandType.TwoPair
            "1112" -> HandType.OnePair
            "11111" -> HandType.HighCard
            else -> null
        }!!
        val handScore = cards.map {
            when (it) {
                'A' -> 14
                'K' -> 13
                'Q' -> 12
                'J' -> 11
                'T' -> 10
                else -> it.digitToInt()
            }
        }
        return Pair(handType, handScore)
    }
}