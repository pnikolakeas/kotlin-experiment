package events

interface Focusable {

    fun setFocus(b: Boolean) =
            println("I ${if (b) "got" else "lost" } focus.")

    fun showableOff() = println("I'm focusable!")

}