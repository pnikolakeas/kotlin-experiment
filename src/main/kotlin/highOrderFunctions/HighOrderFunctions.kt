package highOrderFunctions

fun main(args: Array<String>) {


    val sum : (Int, Int) -> Int = { x , y -> x + y }

    val printMe : (Int) -> Unit = { x -> println(x) }

    var canReturnNull: (Int, Int) -> Int? = { x, y -> null } // A function type with a nullable return type

    var funOrNull: ((Int, Int) -> Int)? = null               // A nullable variable of a function type

    println(sum(3,2))
    printMe(42)

    val url = "http://kotl.in"
    foo(url) { println("This is a message: $it")}
    foo(url, ::buz)

    useTwoAndThree {x: Int, y: Int -> x+y}
    useTwoAndThree {x: Int, y: Int -> x*y}

    useAnyTwoInts(2,3) {x,y -> x+y}
    useAnyTwoInts(2,3) {x,y -> x*y}

    useAnyTwoInts(4,4) {x,y -> x+y}
    useAnyTwoInts(4,4) {x,y -> x*y}

    val input: String = "a1b2324cd34e"
    println(input.filter { it in 'a'..'z' }) // Passes a lambda as an argument for "predicate"
}

fun foo(m: String, bar: (m: String) -> Unit) {
    bar(m)
}

fun buz(m: String) : Unit {
    println("This is another message: $m")
}

fun useTwoAndThree(operator: (Int, Int) -> Int) { // Declares a parameter of a function type
    val result = operator(2, 3) // Calls the parameter of a function type
    println("The result is: $result")
}

fun useAnyTwoInts(operandOne: Int, operandTwo: Int, operator: (Int, Int) -> Int) { // Declares a parameter of a function type
    val result = operator(operandOne, operandTwo) // Calls the parameter of a function type
    println("The result is: $result")
}

fun String.filter(predicate: (Char) -> Boolean) : String {
    val sb = StringBuilder()
    for(index in 0..length-1) {
        val element = get(index)
        if(predicate(element)) sb.append(element) // Calls the function passed as the "predicate" argument
    }
    return sb.toString()
}