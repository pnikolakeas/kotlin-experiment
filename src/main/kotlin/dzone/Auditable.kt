package dzone

import java.io.Serializable
import java.time.LocalDateTime
import java.time.ZoneId

interface Auditable : Serializable {

    var whoCreated: String
    var whoModified: String
    var whenCreated: LocalDateTime
    var whenModified: LocalDateTime
    var timeZone: ZoneId

    fun createAuditInfo()

    fun updateAuditInfo()
}