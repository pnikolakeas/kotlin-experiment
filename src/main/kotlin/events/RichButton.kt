package events

open class RichButton : Clickable { // can be subclassed

    fun disable(){                  // cannot be overridden, final by default
    }

    open fun animate() {            // can be overridden
    }

    override fun clickable() {      // can be overridden, overrides an open method
    }
}