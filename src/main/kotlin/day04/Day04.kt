package day04

object Day04 {

    private fun processCards(input: String): List<Int> =
        input.split("\n").map { line ->
            line.split(":")[1].let { game ->
                val (winningNumbers, myNumbers) = game.split("|")
                val winningSet = winningNumbers.split(" ").filter { it.trim().isNotEmpty() }.toSet()
                val mySet = myNumbers.split(" ").filter { it.trim().isNotEmpty() }.toSet()
                winningSet.intersect(mySet).size
            }
        }

    fun processPart1(input: String): Int =
        processCards(input).sumOf { if (it == 0) 0 else 1 shl (it - 1) }

    fun processPart2(input: String): Int {
        val winners = processCards(input)
        val listOfWinners = winners.map { mutableListOf(it) }
        for (i in winners.indices) {
            for (winner in listOfWinners[i]) {
                for (j in i + 1..<i + winner + 1) {
                    if (j < listOfWinners.size) {
                        listOfWinners[j].add(winners[j])
                    }
                }
            }
        }
        return listOfWinners.flatten().count()
    }
}
