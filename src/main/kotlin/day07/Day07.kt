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
        process(input, ::getJokerHandStrength)

    private fun getHandScore(cards: String, jokerStrength: Int): List<Int> =
        cards.map {
            when (it) {
                'A' -> 14
                'K' -> 13
                'Q' -> 12
                'J' -> jokerStrength
                'T' -> 10
                else -> it.digitToInt()
            }
        }

    private fun getCount(cards: String): MutableMap<Char, Int> =
        cards.fold(mutableMapOf()) { acc, c ->
            acc.putIfAbsent(c, 0)
            acc[c] = acc[c]!! + 1
            acc
        }

    private fun getHandType(cards: String): HandType =
        when (cards) {
            "5" -> HandType.FiveOfAKind
            "14" -> HandType.FourOfAKind
            "23" -> HandType.FullHouse
            "113" -> HandType.ThreeOfAKind
            "122" -> HandType.TwoPair
            "1112" -> HandType.OnePair
            "11111" -> HandType.HighCard
            else -> null
        }!!

    private fun getHandStrength(cards: String): Pair<HandType, List<Int>> {
        val count = getCount(cards).values.sorted().joinToString("")
        val handType = getHandType(count)
        val handScore = getHandScore(cards, 11)
        return Pair(handType, handScore)
    }

    private fun getJokerHandStrength(cards: String): Pair<HandType, List<Int>> {
        val jokers = cards.filter { it == 'J' }.length
        if (jokers == 0) {
            return getHandStrength(cards)
        }
        val noJokers = cards.filter { it != 'J' }
        val list = getCount(noJokers).values.sortedWith { a, b -> a - b }.toMutableList()
        if (list.isEmpty()) {
            list.add(0)
        }
        list[list.size - 1] += jokers
        val count = list.joinToString("")
        val handType = getHandType(count)
        val handScore = getHandScore(cards, 0)
        return Pair(handType, handScore)
    }
}