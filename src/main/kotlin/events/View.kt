package events

open class View {

    open fun clicked() {
        println("View clicked!")
    }

}

fun View.showOff() = println("I am a view!")