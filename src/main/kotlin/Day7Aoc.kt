import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException

fun main() {
}

class TreeNode(
    val name: String,
    val parent: TreeNode?,
    var nodeValue: Double, // only if it is an item. Only LEAFS should have a nodeValue
    var children: MutableMap<String, TreeNode> = mutableMapOf<String, TreeNode>(),
    var total: Double = 0.0
)

fun day7Part1() {
    val currentTreeNode = TreeNode("/", null, 0.0);
    var ref = currentTreeNode;
    try {
        val file = File("/Users/chunyang/Desktop/indeed/revision/AOC/src/main/kotlin/input-5.txt");
        BufferedReader(FileReader(file)).use { br ->
            var line: String?
            while (br.readLine().also { line = it } != null) {
                print(line);
                // case 1 - is $ sign
                if (line!![0] == '$') {
                    val commands = line!!.split(" ");
                    if (commands[1] == "ls") {
                        // Add everything to the child
                    } else if (commands[1] == "cd") {
                        if (commands[2] == ".." && ref.parent != null) {
                            ref = ref.parent!!;
                        } else {
                            val dirToGoTo = commands[2]
                            // create the tree node
                            // If it is a directory, idw it to have a value
                            val newTreeNode = TreeNode(dirToGoTo, currentTreeNode, 0.0);
                            // add this new tree node to the parent
                            if (ref.children[dirToGoTo] == null) {
                                ref.children[dirToGoTo] = newTreeNode
                            }
                            // move to the child directory
                            ref = ref.children[dirToGoTo]!!;
                        }
                    }
                } else { // If it is part of the l
                    val commands = line!!.split(" ");
                    // Since I got the ref, I want to
                    if (commands[0].toDoubleOrNull() != null
                        // Only if it is not visited yet
                        && currentTreeNode.children.isEmpty()
                    ) {
                        // If it is a number
//                        currentTreeNode.children[commands[1], TreeNode(commands[1], currentTreeNode, )]

                    } else {
                        // It is another directory and start with dir first
                        // Add it to the child of current directory
                    }
                }
            }
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
}

class Node