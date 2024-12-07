import java.io.File
import kotlin.math.pow

data class Day7Data(val result: Long, val equation: List<Long>)

class Day7(private val filePath: String) : AdventDay {
    private fun readData(): Array<Day7Data> {
        var data = emptyArray<Day7Data>()
        val regex = Regex("(\\d+): (.*)")

        File(filePath).inputStream().bufferedReader().forEachLine { outer ->
            val parsed = regex.find(outer)!!.destructured.toList()
            data += Day7Data(parsed[0].toLong(), parsed[1].split(' ').map { it.toLong() })
        }

        return data
    }

    override fun part1() {
        var total = 0L
        for (equation in this.readData()) {
            val operationsCount = equation.equation.size - 1
            for (i in 0..<2.toDouble().pow(operationsCount).toLong()) {
                val binary = i.toString(2).padStart(operationsCount, '0')
                var result = equation.equation[0]

                for (j in 0..<operationsCount) {
                    if (binary[j] == '0') result += equation.equation[j + 1] else result *= equation.equation[j + 1]
                }
                println(result)

                if (result == equation.result) {
                    total += result
                    break
                }
            }
            println()
        }
        println(total)
    }

    override fun part2() {}
}

