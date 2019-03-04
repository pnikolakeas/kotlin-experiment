package typesystemlet

class TypeSystemsLet {

    fun sendEmailTo(email: String) {
        println("Sending email to $email")
    }

}

fun main(args: Array<String>) {

    var email: String? = "yolo@example.com"

    var tsl : TypeSystemsLet = TypeSystemsLet()

    // First way to call sendEmailTo
    if(email != null)
        tsl.sendEmailTo(email)

    // Second way to call sendEmailTo
    email?.let{ tsl.sendEmailTo(it)} // The let function will be called only if the email value is not-null, otherwise nothing happens

    email.let { tsl.sendEmailTo("panos");} // it in this case is a nullable string and it hasn't been checked for nullability

}