package day07

fun main() {
    val input = object {}.javaClass.getResource("../day07.txt")?.readText(Charsets.UTF_8).orEmpty()
    println("part2 output: ${Day07.processPart2(input)}")
}