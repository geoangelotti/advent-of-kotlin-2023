package day08

object Day08 {
    private tailrec fun recursivelyMove(
        node: Pair<String, String>,
        nodes: Map<String, Pair<String, String>>,
        iterator: CyclicIterator<Move>,

        check: (String) -> Boolean,
        accumulator: Int
    ): Int {
        val next = accumulator + 1
        val nextNode = when (iterator.next()) {
            Move.Left -> node.first
            Move.Right -> node.second
        }
        if (check(nextNode)) {
            return next
        }
        return recursivelyMove(nodes[nextNode]!!, nodes, iterator, check, next)
    }

    private tailrec fun recursivelySandMove(
        nodes: List<Pair<String, String>>,
        nodess: Map<String, Pair<String, String>>,
        iterator: CyclicIterator<Move>,
        check: (String) -> Boolean,
        accumulator: Int
    ): Int {
        val next = accumulator + 1
        val move = iterator.next()
        val nextNodes = nodes.map {
            when (move) {
                Move.Left -> it.first
                Move.Right -> it.second
            }
        }
        if (nextNodes.all { check(it) }) {
            return next
        }
        return recursivelySandMove(nextNodes.map { nodess[it]!! }, nodess, iterator, check, next)
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
        fun check(node: String): Boolean = node == "ZZZ"
        return recursivelyMove(nodes["AAA"]!!, nodes, CyclicIterator<Move>(moves), ::check, 0)
    }

    fun processPart2(input: String): Long {
        val regex = "([A-Z0-9]{3}) = \\(([A-Z0-9]{3}), ([A-Z0-9]{3})\\)".toRegex()
        val (moves, nodes) = generateInput(input, regex)
        val starts = nodes.keys.filter { it.last() == 'A' }
        fun check(node: String): Boolean = node.last() == 'Z'
        return leastCommonMultiple(starts.map {
            recursivelyMove(
                nodes[it]!!,
                nodes,
                CyclicIterator<Move>(moves),
                ::check,
                0
            )
        })
    }

    private fun leastCommonMultiple(nums: List<Int>): Long {
        if (nums.isEmpty())
            return 1
        val a = nums.first().toLong()
        if (nums.size == 1)
            return a
        val b = leastCommonMultiple(nums.slice(1..<nums.size))
        return a * b / greaterCommonDivisor(a, b)
    }

    private fun greaterCommonDivisor(a: Long, b: Long): Long {
        if (b == (0).toLong()) {
            return a
        }
        return greaterCommonDivisor(b, a % b)
    }
}