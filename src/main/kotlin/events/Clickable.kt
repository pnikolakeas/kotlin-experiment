package events

interface Clickable {
    fun clickable()                                 // regular method
    fun showableOff() = println("I am clickable!")  // method with default implementation

}