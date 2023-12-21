package day08

object Day08 {
    private fun recursivelyMove(node: Pair<String, String>, nodes: Map<String, Pair<String, String>>, iterator: MovesIterator, target: String): Int {
        val nextNode = when (iterator.next()) {
            Move.Left -> node.first
            Move.Right -> node.second
        }
        if (nextNode == target) {
            return 1
        }
        return 1 + recursivelyMove(nodes[nextNode]!!, nodes, iterator, target)
    }

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
        val nodes = maps[1].split("\n").mapNotNull { regex.find(it) }.associate {
            it.groupValues[1] to Pair(it.groupValues[2], it.groupValues[3])
        }
        return recursivelyMove(nodes["AAA"]!!, nodes, MovesIterator(moves), "ZZZ")
    }
}