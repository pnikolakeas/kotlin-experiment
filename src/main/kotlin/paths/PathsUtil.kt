package paths

fun parsePath(path: String) {
    val directory = path.substringBeforeLast("/")
    val fullName  = path.substringAfterLast("/")
    val fileName  = fullName.substringBeforeLast(".")
    val extension = fullName.substringAfterLast(".")
    println("Dir $directory, name: $fileName, ext: $extension")
}

val vbLogo = """
    .  0
    . / \
    . | |
    . | |
    . | |
    .O| |O""".trimMargin(".")

fun main(args: Array<String>) {
    parsePath("/Users/yole/kotlin-book/chapter.adoc")
    parsePath("/Panagiotis/Georgiou/Nikolakeas/panos.txt")
    println(vbLogo)
}