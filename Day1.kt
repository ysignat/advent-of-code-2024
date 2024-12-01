import java.io.File
import kotlin.math.abs

data class Day1Data(val first: Array<Int>, val second: Array<Int>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Day1Data

        if (!first.contentEquals(other.first)) return false
        if (!second.contentEquals(other.second)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = first.contentHashCode()
        result = 31 * result + second.contentHashCode()
        return result
    }
}

class Day1(private val filePath: String) : AdventDay {
    private fun readData(): Day1Data {
        var firstArray = emptyArray<Int>()
        var secondArray = emptyArray<Int>()

        File(filePath).inputStream().bufferedReader().forEachLine {
            val splitted = it.split("\\s+".toRegex())
            firstArray += splitted[0].toInt()
            secondArray += splitted[1].toInt()
        }

        return Day1Data(firstArray, secondArray)
    }

    override fun part1() {
        val data = this.readData()
        data.first.sort()
        data.second.sort()

        val result = data.first.zip(data.second, Int::minus).map { abs(it) }.reduce(Int::plus)

        print(result)
    }

    override fun part2() {
        val data = this.readData()
        val secondArrayValuesCounted = data.second.groupingBy { it }.eachCount()
        val result = this.readData().first.map { it * (secondArrayValuesCounted[it] ?: 0) }.reduce(Int::plus)

        print(result)
    }
}
