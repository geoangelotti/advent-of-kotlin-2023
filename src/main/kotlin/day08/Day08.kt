package day08

object Day08 {
    fun processPart1(input: String): Int {
        val maps = input.filter { it != '\r' }.split("\n\n")
        val allowedChars = setOf('R', 'L')
        val moves = maps[0].filter { allowedChars.contains(it) }.split("").mapNotNull {
            when (it) {
                "R" -> Move.Right
                "L" -> Move.Left
                else -> null
            }
        }
        val regex = "([A-Z]{3}) = \\(([A-Z]{3}), ([A-Z]{3})\\)".toRegex()
        val levels = maps[1].split("\n").mapNotNull { regex.find(it) }.associate {
            it.groupValues[1] to Pair(it.groupValues[2], it.groupValues[3])
        }
        var counter = 0
        var node = levels["AAA"]!!
        val movesIterator = MovesIterator(moves)
        while (movesIterator.hasNext()) {
            counter += 1
            val nextNode = when (movesIterator.next()) {
                Move.Left -> node.first
                Move.Right -> node.second
            }
            if (nextNode == "ZZZ") {
                break
            }
            node = levels[nextNode]!!
        }
        return counter
    }
}