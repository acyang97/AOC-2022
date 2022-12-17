import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException

fun main() {
    part2()
}

fun part1() {
    var total = 0
    try {
        val file = File("/Users/chunyang/Desktop/indeed/revision/AOC/src/main/kotlin/input-4.txt");
        BufferedReader(FileReader(file)).use { br ->
            var line: String?
            while (br.readLine().also { line = it } != null) {
                var x = line!!.split(",")
                val first = x[0]
                val second = x[1]
                val firstLeft = first.split("-")[0].toInt();
                val firstRight = first.split("-")[1].toInt();
                val secondLeft = second.split("-")[0].toInt();
                val secondRight = second.split("-")[1].toInt();
                if (firstLeft >= secondLeft && firstRight <= secondRight) {
                    total++;
                } else if (firstLeft <= secondLeft && firstRight >= secondRight) {
                    total++
                }
            }
        }
        println("total is $total")
    } catch (e: IOException) {
        e.printStackTrace()
    }
}

fun part2() {
    var total = 0
    try {
        val file = File("/Users/chunyang/Desktop/indeed/revision/AOC/src/main/kotlin/input-4.txt");
        BufferedReader(FileReader(file)).use { br ->
            var line: String?
            while (br.readLine().also { line = it } != null) {
                var x = line!!.split(",")
                val first = x[0]
                val second = x[1]
                val firstLeft = first.split("-")[0].toInt();
                val firstRight = first.split("-")[1].toInt();
                val secondLeft = second.split("-")[0].toInt();
                val secondRight = second.split("-")[1].toInt();
                if (!(firstRight < secondLeft || secondRight < firstLeft)) {
                    total++
                }
            }
        }
        println("total is $total")
    } catch (e: IOException) {
        e.printStackTrace()
    }
}