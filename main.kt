interface AdventDay {
    fun part1()
    fun part2()
}

fun main() {
    val dayNumber = (System.getenv("DAY_NUMBER") ?: "1").toInt()
    val partNumber = (System.getenv("PART_NUMBER") ?: "1").toInt()
    val dataFilePath = System.getenv("DATA_FILE_PATH") ?: "input.data"

    val day: AdventDay
    when (dayNumber) {
        1 -> day = Day1(dataFilePath)

        else -> {
            throw Error("No such day implemented")
        }
    }

    when (partNumber) {
        1 -> day.part1()
        2 -> day.part2()

        else -> {
            throw Error("No such part implemented")
        }
    }
}