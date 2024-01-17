package day04

object Day04 {

    fun processPart1(input: String): Int =
        input.split("\n").sumOf { line ->
            line.split(":")[1].let { game ->
                val (winningNumbers, myNumbers) = game.split("|")
                val winningSet = winningNumbers.split(" ").filter { it.trim().isNotEmpty() }.toSet()
                val mySet = myNumbers.split(" ").filter { it.trim().isNotEmpty() }.toSet()
                val winners = winningSet.intersect(mySet).size
                if (winners == 0) 0 else 1 shl (winners - 1)
            }
        }
}
