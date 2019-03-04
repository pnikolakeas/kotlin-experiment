package annotations

import java.util.*
import kotlin.reflect.KClass

data class Soldier(
        @JsonName("alias") val firstName: String,
        @JsonExclude val age : Int? = null,
        @DeserializeInterface(CompanyImpl::class) val company: Company, // Controls the deserialization of properties that have an interface type
        @CustomSerializer(DateSerializer::class) val birthDate: Date
)

fun main(args: Array<String>) {

    var soldier : KClass<Soldier> = Soldier::class;

    var soldier1 : Class<Soldier> = Soldier::class.java;

}


