import java.io.File
import kotlin.math.abs

class Day2(private val filePath: String) : AdventDay {
    private fun readData(): Array<List<Int>> {
        var data = emptyArray<List<Int>>()

        File(filePath).inputStream().bufferedReader().forEachLine { outer ->
            data += outer.split("\\s+".toRegex()).map { inner -> inner.toInt() }
        }

        return data
    }

    override fun part1() {
        var counter = 0

        rows@ for (row in this.readData()) {
            if (row.size <= 1) {
                counter += 1
                continue
            }

            var increasing: Boolean? = null

            columns@ for (index in 1..<row.size) {
                val diff = row[index] - row[index - 1]

                if (diff == 0 || abs(diff) > 3) continue@rows

                if (increasing == null) {
                    increasing = diff > 0
                }

                if (increasing && (diff < 0)) continue@rows
                if (!increasing && (diff > 0)) continue@rows
            }

            counter += 1
        }

        println(counter)
    }

    override fun part2() {}
}
