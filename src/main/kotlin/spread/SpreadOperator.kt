package spread

val array : Array<String> = arrayOf("one", "three", "fifty-seven")

fun main(args: Array<String>) {

    val list = listOf("args", array)
    println(list)

    val list11 = listOf("args", *array)
    println(list11)

}