package events

abstract class Animated {      // cannot be instantiated

    abstract fun animate()     // must be overridden in subclass

    open fun stopAnimating(){} // is marked as open, so, can be overridden. not by default

    fun animateTwice() {}      // not open. cannot be overridden.
}