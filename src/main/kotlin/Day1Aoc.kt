import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException
import java.util.PriorityQueue

// The function where code will start
fun main() {
    val file = File("/Users/chunyang/Desktop/indeed/revision/AOC/src/main/kotlin/input-1.txt");
    var currentCalories = 0;
    var totalCalories = 0
    var pq = PriorityQueue {t1: Int, t2 : Int -> t2 - t1}
    try {
        BufferedReader(FileReader(file)).use { br ->
            var line: String?
            while (br.readLine().also { line = it } != null) {
                if (line.isNullOrBlank()) {
                    pq.add(currentCalories);
                    currentCalories = 0;
                } else {
                    currentCalories += line!!.toInt();
                }
            }
        }
        totalCalories += pq.poll();
        println("$totalCalories is totalCalories")

        totalCalories += pq.poll();
        println("$totalCalories is totalCalories")

        totalCalories += pq.poll();
        println("$totalCalories is totalCalories")

    } catch (e: IOException) {
        e.printStackTrace()
    }
}

