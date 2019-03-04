package properties

interface Player {
    val email: String // This is an abstract property - Must be overridden in subclass
    val nickname: String
        get() = email.substringBefore('@')  // No backup fields are allowed inside interface - Only custom getters - Can be inherited in subclass
}

class LocalPlayer(override val email: String) : Player

class GlobalPlayer(override val email: String, override val nickname: String) : Player

class FacebookPlayer(override val email: String) : Player {
    override val nickname: String
        get() = email.substringBefore('@') // Custom getter - Initialized on each call
}

class Twitterlayer(override val email: String) : Player {
    override val nickname = email.substringBefore('@') // Property initializer - Backup Field - Initialized only once

    var sex : String = "MALE"
        private set(value: String) {
            println("""
                Address was changed for $sex:
                "$field" -> "$value"    // reads the backing field value.
            """.trimIndent())
            field = value  //updates the backing field value
        }
}