package events

class Button : View(), Clickable, Focusable {

    override fun clickable() = println("I was pretty clicked!")

    override fun clicked() = println("Button clicked!")

    override fun showableOff() {
        super<Clickable>.showableOff()
        super<Focusable>.showableOff()
    }
}

fun Button.showOff() = println("I am a button!")