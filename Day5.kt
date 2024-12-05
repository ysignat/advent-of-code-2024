import java.io.File

data class Day5RulesData(val before: Int, val after: Int)

data class Day5UpdateData(val data: HashMap<Int, Int>, val middle: Int)

data class Day5Data(val rules: Array<Day5RulesData>, val updates: Array<Day5UpdateData>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Day5Data

        if (!rules.contentEquals(other.rules)) return false
        if (!updates.contentEquals(other.updates)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = rules.contentHashCode()
        result = 31 * result + updates.contentHashCode()
        return result
    }
}

class Day5(private val filePath: String) : AdventDay {
    private fun readData(): Day5Data {
        var rules = emptyArray<Day5RulesData>()
        var updates = emptyArray<Day5UpdateData>()
        var isRule = true

        for (line in File(filePath).inputStream().bufferedReader().readLines()) {
            if (isRule) {
                if (line == "") {
                    isRule = false
                    continue
                }

                val splitted = line.split('|')
                rules += Day5RulesData(splitted[0].toInt(), splitted[1].toInt())
                continue
            }

            val update: HashMap<Int, Int> = HashMap()
            val splitted = line.split(',').map { it.toInt() }
            splitted.forEachIndexed { index, element ->
                update[element] = index
            }

            updates += Day5UpdateData(update, splitted[(splitted.size - 1) / 2])
        }

        return Day5Data(rules, updates)
    }

    override fun part1() {
        val data = this.readData()
        var result = 0
        updates@ for (update in data.updates) {
            for (rule in data.rules) {
                val beforeIndex = update.data[rule.before]
                val afterIndex = update.data[rule.after]

                if (beforeIndex == null || afterIndex == null) {
                    continue
                }

                if (beforeIndex > afterIndex) {
                    continue@updates
                }

            }
            result += update.middle
        }

        println(result)
    }

    override fun part2() {
    }
}

