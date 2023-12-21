package day08

object Day08 {
    fun processPart1(input: String): Int {
        val maps = input.filter { it != '\r' }.split("\n\n")
        val allowedChars = setOf('R', 'L')
        val moves = maps[0].filter { allowedChars.contains(it) }.split("").filter { it.isNotEmpty() }
        val regex = "([A-Z]{3}) = \\(([A-Z]{3}), ([A-Z]{3})\\)".toRegex()
        val levels = maps[1].split("\n").associate { level ->
            regex.find(level).let { it!!.groupValues[1] to Pair(it.groupValues[2], it.groupValues[3]) }
        }
        var counter = 0
        var i = 0
        var node = levels["AAA"]!!
        while (true) {
            counter += 1
            val move = moves[i]
            val nextNode = when (move) {
                "R" -> node.second
                "L" -> node.first
                else -> ""
            }
            if (nextNode == "ZZZ") {
                break
            }
            node = levels[nextNode]!!
            if (i == moves.size - 1) {
                i = 0
            } else {
                i += 1
            }
        }
        return counter
    }
}