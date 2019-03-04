package users

class User(val id: Int, val name: String?, val address: String?)

fun User.validateBeforeSave() {

    fun validate(value: String?, fieldName: String) {
        value.ifNotNullOrElse({ println("$fieldName not null") }, { throw IllegalArgumentException("$fieldName null") })
    }

    validate(name, "Name")
    validate(address, "Address")
}

fun saveUser(user: User) {
    try {
        user.validateBeforeSave()
        println("User ${user.name} saved to the database!")
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

inline fun <T : Any, R> T?.ifNotNullOrElse(ifNotNullPath: (T) -> R, elsePath: () -> IllegalArgumentException)
        = let { if(it == null) elsePath() else ifNotNullPath(it) }

fun main(args: Array<String>) {
    var user = User(1,"panos", "spathari")
    var user1 = User(2,"sdfs",null)
    var user3 = User(3, "koukos","kavourdiri")

    val list = listOf<User>(user, user3)
    for(user in list) {
        saveUser(user)
    }
}

val listWithNulls: List<String?> = listOf("Kotlin", null)
fun justPrintNotNulls() {
    for(item in listWithNulls) {
        item?.let{ println(it) } // prints Kotlin and ignores null
    }
}

/* val length = value?.length ?: 0;
     if(length == 0) {
         println("Undefined value: $fieldName")
     } */
