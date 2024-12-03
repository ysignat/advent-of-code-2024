import java.io.File

class Day3(private val filePath: String) : AdventDay {
    private fun readData(): String {
        return File(filePath).inputStream().bufferedReader().readText()
    }

    override fun part1() {
        val result = Regex("(mul\\((\\d+),(\\d+)\\))").findAll(this.readData()).toList()
            .map { it.destructured.toList() }.map { it[1].toInt() * it[2].toInt() }.reduce(Int::plus)

        println(result)
    }

    override fun part2() {
        var areInstructionsEnabled = true
        var result = 0
        for (match in Regex("(mul\\((\\d+),(\\d+)\\)|do\\(\\)|don't\\(\\))").findAll(this.readData()).toList()
            .map { it.destructured.toList() }) {
            println(match)
            val operation = match[0]

            if (operation == "do()") {
                areInstructionsEnabled = true
                continue
            }

            if (operation == "don't()") {
                areInstructionsEnabled = false
                continue
            }

            println(areInstructionsEnabled)
            if (areInstructionsEnabled && operation.startsWith("mul")) {
                result += match[1].toInt() * match[2].toInt()
            }
        }

        println(result)
    }
}
