package companion

data class Persona(val name: String) {

    companion object {

    }

}

fun Persona.Companion.fromJSON(jsonStr: String) : Persona = Persona(jsonStr)