package extensionfunctiontype

public fun main(args: Array<String>) {

    // You should use 'it' to refer to the nested object
    println(buildString { it.append("Panos")
                            .append(" ")
                            .append("Nikolakeas")
    })

    println(buildStringExt { append("Panos")
                            .append(" ")
                            .append("Nikolakeas")
    })

    val appendExcl : StringBuilder.() -> Unit = { this.append("!") } // A variable of an extension function type

    // You can call it as a typical extension function
    val sb = StringBuilder("Hi")
    sb.appendExcl()
    println(sb)

    // You can pass it to a function as an argument
    println(buildStringExt(appendExcl))
    println(buildStringExt { this.append("!") })

    // Call the APPLY implementation v1
    println(buildStringExtImproved1(appendExcl))
    println(buildStringExtImproved1{ this.append("!") })

    // Call the APPLY implementation v2
    println(buildStringExtImproved2(appendExcl))
    println(buildStringExtImproved2{ this.append("!") })

    val map = mutableMapOf(1 to "One")
    val map1 = map.apply {
        this[2] = "Two"
        put(3,"Three")
    }
    val map2 = with(map) {
        this[4] = "Four"
        put(5, "Five")
    }

    println(map)
}

/**
 * FUNCTION TYPE as input
 */
fun buildString(builderAction: (StringBuilder) -> Unit) : String {
    var sb: StringBuilder = StringBuilder()
    builderAction(sb)
    return sb.toString()
}

/*
 * EXTENSION FUNCTION TYPE as input
 * You use a lambda with receiver function and you can get rid of the it inside the lambda body
 */
fun buildStringExt(builderAction: StringBuilder.() -> Unit) : String {
    var sb: StringBuilder = StringBuilder()
    sb.builderAction()
    return sb.toString()
}
/*
 * Actual Library Implementation ver1 [APPLY]
 */
fun buildStringExtImproved1(builderAction: StringBuilder.() -> Unit) : String {
    return StringBuilder().apply { builderAction }.toString()
}

/*
 * Actual Library Implementation ver2 [APPLY]
 */
fun buildStringExtImproved2(builderAction: StringBuilder.() -> Unit) : String =
     StringBuilder().apply { builderAction }.toString()

inline fun <T> T.apply(block: T.() -> Unit) : T {
    block()                                      // Equivalent to 'this.block()'; Invokes the lambda with the receiver of 'apply' as the receiver object
    return this                                  // Returns the receiver
}

inline fun <T, R> with(receiver: T, block: T.() -> R) : R =
        receiver.block()                        // Returns the result of calling the lambda