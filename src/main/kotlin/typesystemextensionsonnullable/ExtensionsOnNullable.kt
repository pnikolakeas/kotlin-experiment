package typesystemextensionsonnullable

class ExtensionsOnNullable {

    fun verifyUserInput(input: String?) {
        if(input.isNullOrBlank())              // You can call this extension function of String directly on the nullable type! NO SAFE CALL is needed
            println("Input is: $input")
    }

    fun <T> printHashCodeNullable(t: T) {
        println(t?.hashCode())                 // By default all Type Parameters are nullable
    }

    fun <T : Any> printHashCode(t : T) {
        println(t.hashCode())
    }
}

fun main(args: Array<String>) {
    var type : ExtensionsOnNullable = ExtensionsOnNullable()
    type.verifyUserInput("")
    type.verifyUserInput(null)
    type.verifyUserInput("panos")

    type.printHashCodeNullable(null)
    type.printHashCodeNullable("panos")

    type.printHashCode("")
    type.printHashCode("panos")
}