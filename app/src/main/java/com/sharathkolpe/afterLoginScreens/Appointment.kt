import com.google.firebase.Timestamp
import java.time.LocalTime

data class Appointment(
    val doctorId: String = "",
    val patientId: String = "",
    val slot: String = "",
    val timestamp: Timestamp? = null,
    val tokenNumber: Int = 0,
    val availabilityType: String = "",
    val address: String = "",
    val contact: String = ""
)




data class BookedSlots(
    val doctorId: String = "",
    val patientId: String = "",
    val date: String = "",
    val slotKey: String = "",
    val startTime: String = "",
    val endTime: String = "",
    val status: String = "",
    val bookedTime: Long? = null,
    val timestamp: Timestamp? = null
) {
    val slot: String
        get() = "$startTime - $endTime"
}



data class TimeSlot(
    var startTime: LocalTime = LocalTime.of(9, 0),
    var endTime: LocalTime = LocalTime.of(9, 30)
)

data class DayAvailability(
    var isAvailable: Boolean = false,
    var numberOfSlots: Int = 0,
    var slots: List<TimeSlot> = emptyList()
)


