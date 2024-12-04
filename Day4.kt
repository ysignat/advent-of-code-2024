import java.io.File

class Day4(private val filePath: String) : AdventDay {
    private fun readData(): Array<String> {
        var data = emptyArray<String>()

        File(filePath).inputStream().bufferedReader().forEachLine {
            data += it
        }

        return data
    }

    override fun part1() {
        val data = this.readData()
        val width = data[0].length
        val height = data.size
        var counter = 0

        for (i in 0..<width) {
            for (j in 0..<height) {
                if (data[i][j] == 'X') {
                    if (i >= 3) {
                        if (data[i - 1][j] == 'M' && data[i - 2][j] == 'A' && data[i - 3][j] == 'S') {
                            println("$i:$j")
                            counter += 1
                        }
                        if (j >= 3 && data[i - 1][j - 1] == 'M' && data[i - 2][j - 2] == 'A' && data[i - 3][j - 3] == 'S') {
                            println("$i:$j")
                            counter += 1
                        }
                        if (j <= width - 4 && data[i - 1][j + 1] == 'M' && data[i - 2][j + 2] == 'A' && data[i - 3][j + 3] == 'S') {
                            println("$i:$j")
                            counter += 1
                        }
                    }

                    if (i <= height - 4) {
                        if (data[i + 1][j] == 'M' && data[i + 2][j] == 'A' && data[i + 3][j] == 'S') {
                            println("$i:$j")
                            counter += 1
                        }

                        if (j <= width - 4 && data[i + 1][j + 1] == 'M' && data[i + 2][j + 2] == 'A' && data[i + 3][j + 3] == 'S') {
                            println("$i:$j")
                            counter += 1
                        }

                        if (j >= 3 && data[i + 1][j - 1] == 'M' && data[i + 2][j - 2] == 'A' && data[i + 3][j - 3] == 'S') {
                            println("$i:$j")
                            counter += 1
                        }
                    }

                    if (j >= 3 && data[i][j - 1] == 'M' && data[i][j - 2] == 'A' && data[i][j - 3] == 'S') {
                        println("$i:$j")
                        counter += 1
                    }


                    if (j <= width - 4 && data[i][j + 1] == 'M' && data[i][j + 2] == 'A' && data[i][j + 3] == 'S') {
                        println("$i:$j")
                        counter += 1
                    }
                }
            }
        }

        println(counter)
    }

    override fun part2() {}
}
