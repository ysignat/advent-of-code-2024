interface AdventDay {
    fun part1()
    fun part2()
}

fun main() {
    val dayNumber = (System.getenv("DAY_NUMBER") ?: "1").toInt()
    val partNumber = (System.getenv("PART_NUMBER") ?: "1").toInt()
    val dataFilePath = System.getenv("DATA_FILE_PATH") ?: "input.data"
    val day: AdventDay = when (dayNumber) {
        1 -> Day1(dataFilePath)
        2 -> Day2(dataFilePath)
        3 -> Day3(dataFilePath)
        4 -> Day4(dataFilePath)
        5 -> Day5(dataFilePath)
        6 -> Day6(dataFilePath)
        7 -> Day7(dataFilePath)

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