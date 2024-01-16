package day04

import kotlin.math.pow

object Day04 {

    fun processPart1(input: String): Int =
        input.split("\n").sumOf { line ->
            line.split(":")[1].let { game ->
                val (winningNumbers, myNumbers) = game.split("|")
                val winningSet = winningNumbers.split(" ").filter { it.trim().isNotEmpty() }.toSet()
                val mySet = myNumbers.split(" ").filter { it.trim().isNotEmpty() }.toSet()
                val winners = winningSet.intersect(mySet).size
                if (winners == 0) 0 else 2f.pow(winners - 1)
            }.toInt()
        }
}
