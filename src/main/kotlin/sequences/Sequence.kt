package sequences

import companion.Person
import java.io.File
import java.nio.file.Paths

class Sequence {

}
// Useful to see the order of execution
fun main(args: Array<String>) {

    val path = Paths.get("General.kt").toAbsolutePath().toString()
    println(path)

    val file = File("General.kt")
    println(file.isInsideHiddenDirectory())


}

fun File.isInsideHiddenDirectory() = generateSequence(this) {this.parentFile}.any {isHidden}