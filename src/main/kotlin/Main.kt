fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}

fun <T> processDay(day: String, solution: (String) -> T) {
    val input = object {}.javaClass.getResource(day)?.readText(Charsets.UTF_8).orEmpty()
    println("output: ${solution(input)}")
}