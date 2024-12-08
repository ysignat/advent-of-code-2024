import java.io.File

data class Day6GuardPosition(var x: Int, var y: Int)

data class Day6Data(val field: Array<String>, val guard: Day6GuardPosition) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Day6Data

        if (!field.contentEquals(other.field)) return false
        if (guard != other.guard) return false

        return true
    }

    override fun hashCode(): Int {
        var result = field.contentHashCode()
        result = 31 * result + guard.hashCode()
        return result
    }
}

enum class Direction {
    UP, DOWN, RIGHT, LEFT
}

class Day6(private val filePath: String) : AdventDay {
    private fun readData(): Day6Data {
        var data = emptyArray<String>()
        var guardPosition = Day6GuardPosition(0, 0)
        var lineCounter = 0

        File(filePath).inputStream().bufferedReader().forEachLine {
            data += it
            if (it.contains('^')) guardPosition = Day6GuardPosition(it.indexOf('^'), lineCounter)
            lineCounter += 1
        }

        return Day6Data(data, guardPosition)
    }

    override fun part1() {
        val data = this.readData()
        var direction = Direction.UP

        while (true) {
            val horizontal = data.field[data.guard.y]
            val vertical = data.field.map { it[data.guard.x] }
            when (direction) {
                Direction.UP -> {
                    val road = vertical.slice(0..<data.guard.y).reversed()
                    if (road.contains('#')) {
                        data.guard.y -= road.indexOf('#')
                        direction = Direction.RIGHT
                    } else break
                }

                Direction.RIGHT -> {
                    val road = horizontal.slice(data.guard.x..<horizontal.length)
                    if (road.contains('#')) {
                        data.guard.x += road.indexOf('#') - 1
                        direction = Direction.DOWN
                    } else break
                }

                Direction.DOWN -> {
                    val road = vertical.slice(data.guard.y..<vertical.size)
                    if (road.contains('#')) {
                        data.guard.y += road.indexOf('#') - 1
                        direction = Direction.LEFT
                    } else break
                }

                Direction.LEFT -> {
                    val road = horizontal.slice(0..<data.guard.x).reversed()
                    if (road.contains('#')) {
                        data.guard.x -= road.indexOf('#')
                        direction = Direction.UP
                    } else break
                }
            }
        }
    }

    override fun part2() {}
}

