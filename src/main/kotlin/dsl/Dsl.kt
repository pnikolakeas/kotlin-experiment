package dsl

import com.intellij.openapi.util.text.StringUtil
import kotlinx.html.stream.createHTML
import kotlinx.html.table
import kotlinx.html.td
import kotlinx.html.tr
import operators.Point
import java.io.File
import java.lang.StringBuilder

fun main(args: Array<String>) {

    var name: String = "Panos"

    println(StringUtil.capitalize(name))  // Normal Syntax
    println(name.capitalize())            // Extension Function Syntax

    1.to("three")                    // Creates a tuple of type [Pair] from this and [that].
    1 to "three"                          // Infix Form

    val colors: MutableSet<String> = mutableSetOf("red", "blue")
    colors += "green"                     // Implementation of plusAssign -> Operator overloading. Do not re-assign
    println(colors)

    val colors1 : MutableSet<Int> = mutableSetOf(1, 2)
    colors1 += 3                         // Implementation of plusAssign -> Operator overloading. Do not re-assign
    println(colors1)

    val colors3 : Set<String>
    val colors2 = setOf("red", "blue")
    colors3 = colors2 + "pink"          // Implementation of plus -> Operator overloading. Re-assigns!
    println(colors2)
    println(colors3)

    val fileName = "data.txt"
    var file = File(fileName)
    val isNewFileCreated = file.createNewFile()
    if(isNewFileCreated) println("$fileName is created successfully.") else println("$fileName already exists")
    file.useLines { it.forEach { println(it) } }  // Lambda outside of parenthesis, as last argument

    val sb = StringBuilder()
    sb.append("Panos")
    sb.append("Mitsos")

    with(sb) {                     // Lambda with receiver
        append("Giorgos")
        append("Mixalhs")
    }

    val map = mutableMapOf<String, Int>("Panos" to 1)
    println(map.get("Panos"))
    println(map["Panos"])                           // Convention for get method

    println(createSimpleTable("Panos"))

    println(createSimpleTableFromMap(mapOf(1 to "one", 2 to "two", 3 to "three")))
}


private fun createSimpleTable(cell : String)  = createHTML().table {
    tr {
        td {
           + cell
        }
    }
}

private fun createSimpleTableFromMap(map: Map<Int,String>)  = createHTML().table {
    for((num, string) in map) {
        tr {
            td {
                +"$num"
            }
            td {
                +"$string"
            }
        }
    }
}

