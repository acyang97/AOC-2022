import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException

/**
 * First column
 * A is for rock, B is for paper, C is for scissors
 * Second column
 * X is for Rock, Y is for Paper, Z is for scissors
 * 1 for rock, 2 for paper, 3 for scissors
 * 0 lose, 3 draw, 6 win
 */
fun main() {
    var map = mapOf<Char, Int>(
        'X' to 1,
        'Y' to 2,
        'Z' to 3
    )
    var mapToDraw = mapOf<Char, Int>(
        'A' to 1,
        'B' to 2,
        'C' to 3
    )
    val mapToLose = mapOf<Char, Int>(
        'A' to 3,
        'B' to 1,
        'C' to 2
    )
    val mapToWin = mapOf<Char, Int>(
        'A' to 2,
        'B' to 3,
        'C' to 1
    )
    var score: Int = 0;
    try {
        val file = File("/Users/chunyang/Desktop/indeed/revision/kotlin-youtube-crash-course/src/main/kotlin/input-2.txt");
        BufferedReader(FileReader(file)).use { br ->
            var line: String?
            while (br.readLine().also { line = it } != null) {
                val opp = line.toString()[0];
                val me = line.toString()[2];
                // If I need to lose
                if (me == 'X') {
                    score += mapToLose[opp]!! + 0;
                    // If I need to draw
                } else if (me == 'Y') {
                    score += mapToDraw[opp]!! + 3;
                    // If I need to win
                } else {
                    score += mapToWin[opp]!! + 6;
                }
            }
        }
        println("Score is $score")
    } catch (e: IOException) {
        e.printStackTrace()
    }
}