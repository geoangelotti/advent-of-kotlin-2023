package day08

object Day08 {
    private tailrec fun recursivelyMove(node: Pair<String, String>, nodes: Map<String, Pair<String, String>>, iterator: MovesIterator, target: String, accumulator: Int): Int {
        val next = accumulator + 1
        val nextNode = when (iterator.next()) {
            Move.Left -> node.first
            Move.Right -> node.second
        }
        if (nextNode == target) {
            return next
        }
        return recursivelyMove(nodes[nextNode]!!, nodes, iterator, target, next)
    }

    private fun generateInput(input: String, regex: Regex): Pair<List<Move>, Map<String, Pair<String, String>>> {
        val maps = input.filter { it != '\r' }.split("\n\n")
        val allowedChars = setOf('R', 'L')
        val moves = maps[0].filter { allowedChars.contains(it) }.split("").mapNotNull {
            when (it) {
                "R" -> Move.Right
                "L" -> Move.Left
                else -> null
            }
        }
        val nodes = maps[1].split("\n").mapNotNull { regex.find(it) }.associate {
            it.groupValues[1] to Pair(it.groupValues[2], it.groupValues[3])
        }
        return Pair(moves, nodes)
    }

    fun processPart1(input: String): Int {
        val regex = "([A-Z]{3}) = \\(([A-Z]{3}), ([A-Z]{3})\\)".toRegex()
        val (moves, nodes) = generateInput(input, regex)
        return recursivelyMove(nodes["AAA"]!!, nodes, MovesIterator(moves), "ZZZ", 0)
    }
}