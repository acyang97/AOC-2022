import utils.readStrings
import utils.toPair
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException
import kotlin.math.abs

private data class Position(var x: Int, var y: Int) {
    fun isTouching(position: Position): Boolean {
        return abs(this.x - position.x) <= 1 && abs(this.y - position.y) <= 1
    }
}

private fun applyMotion(position: Position, direction: Char) {
    when (direction) {
        'R' -> position.x++
        'L' -> position.x--
        'U' -> position.y++
        'D' -> position.y--
        else -> throw IllegalArgumentException("Wrong direction!")
    }
}

private fun trackMotion(head: Position, tail: Position) {
    if (!head.isTouching(tail)) {
        if (head.x > tail.x) tail.x++
        if (head.y > tail.y) tail.y++
        if (head.x < tail.x) tail.x--
        if (head.y < tail.y) tail.y--
    }
}

private fun partOne(motions: List<kotlin.Pair<Char,Int>>): Int {
    val tailPositions = mutableSetOf<Position>()
    motions.fold(Position(0,0) to Position(0,0)) {knots, motion ->
        repeat(motion.second) {
            applyMotion(knots.first, motion.first) to trackMotion(knots.first, knots.second)
            tailPositions.add(knots.second.copy())
        }
        knots
    }
    return tailPositions.size
}

private fun partTwo(motions: List<kotlin.Pair<Char,Int>>): Int {
    val tailPositions = mutableSetOf<Position>()
    motions.fold(List(10) {Position(0,0)}) {knots, motion ->
        repeat(motion.second) {
            applyMotion(knots.first(), motion.first) to knots.windowed(2).map { it.toPair() }.map { trackMotion(it.first, it.second)}
            tailPositions.add(knots.last().copy())
        }
        knots
    }
    return tailPositions.size
}

fun main(){
    fun processInputDay9(): MutableList<String> {
        var ls = mutableListOf<String>();
        try {
            val file = File("/Users/chunyang/Desktop/indeed/revision/AOC/src/main/kotlin/input-9.txt");
            BufferedReader(FileReader(file)).use { br ->
                var line: String?
                while (br.readLine().also { line = it } != null) {
                    ls.add(line!!)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return ls;
    }
    val input = processInputDay9().map { it.split(" ").toPair().run { this.first.first() to this.second.toInt() } }
//    println(partOne(input))
    println(partTwo(input))
}