package tags

import kotlinx.html.*
import kotlinx.html.stream.createHTML
import java.lang.StringBuilder

open class Tag(val name: String) {

    private val children = mutableListOf<Tag>()

    protected fun <T : Tag> doInit(child : T, init: T.() -> Unit) : Unit {
        child.init()
        children.add(child)
    }

    override fun toString(): String {
        return "<$name>${children.joinToString("")}</$name>"
    }
}

class TABLE : Tag("table") {
    fun tr(init: TR.() -> Unit) : Unit = doInit(TR(), init)
}

class TR : Tag("tr") {
    fun td(init: TD.() -> Unit) : Unit = doInit(TD(), init)
}

class TD : Tag("td")

/**
 * Method for table initialization, [init]
 */
fun table(init: TABLE.() -> Unit) : TABLE = TABLE().apply(init)

fun createTable() = table { tr { td {  } } }

fun createAnotherTable() = table {
    for(i in 1..3) {
            tr {
                td {

                }
            }
        }
    }


fun buildDropDown() = createHTML().div(classes = "dropdown") {
    button(classes = "btn dropdown-toggle") {
        +"Dropdown"
        span(classes = "caret")
    }
    dropdownMenu {
        item("#","Action")
        item("#","Another action")
        divider()
        dropdownHeader("Header")
        item("#","Separated link")
    }
}

fun DIV.dropdownMenu(block: UL.() -> Unit) : Unit = ul("dropdown-menu", block)

fun UL.dropdownHeader(value:String) {
    li {
        classes = setOf("dropdown-header"); +value
    }
}

fun UL.divider() {
    li {
        role = "separator"; classes = setOf("divider")
    }
}

fun UL.item(href:String, value:String) {
    li {
        a(href) {
            +value
        }
    }
}

fun main(args: Array<String>) {
    println(createTable())
    println(createAnotherTable())
    println(buildDropDown())
}