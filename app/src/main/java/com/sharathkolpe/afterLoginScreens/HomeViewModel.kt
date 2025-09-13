

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.*

class DoctorHomeViewModel : ViewModel() {

    private val _appointments = mutableStateListOf<Appointment>()
    val appointments: List<Appointment> = _appointments

    init {
        startAutoRefresh()
    }

    private fun startAutoRefresh() {
        viewModelScope.launch {
            while (true) {
                fetchDoctorAppointments()
                delay(5000) // Refresh every 5 seconds
            }
        }
    }

    fun fetchDoctorAppointments() {
        val doctorId = FirebaseAuth.getInstance().currentUser?.uid ?: return

        val db = Firebase.firestore

        val today = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.time

        val tomorrow = Calendar.getInstance().apply {
            add(Calendar.DATE, 1)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.time

        db.collection("appointments")
            .document(doctorId)
            .collection("tokens")
            .whereGreaterThanOrEqualTo("timestamp", today)
            .whereLessThan("timestamp", tomorrow)
            .orderBy("timestamp", Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener { snapshot ->
                val appointmentList = snapshot.documents.mapNotNull { doc ->
                    doc.toObject(Appointment::class.java)
                }
                _appointments.clear()
                _appointments.addAll(appointmentList)

                Log.d("DoctorAppointments", "Fetched ${appointmentList.size} today's appointments")
            }
            .addOnFailureListener { e ->
                Log.e("DoctorAppointments", "Error fetching appointments: ${e.message}", e)
            }
    }
}

class SlotDoctorHomeViewModel : ViewModel() {
    private val firestore = FirebaseFirestore.getInstance()
    private val _appointments = MutableStateFlow<List<BookedSlots>>(emptyList())
    val appointments: StateFlow<List<BookedSlots>> = _appointments

    private val uid = FirebaseAuth.getInstance().currentUser?.uid
    private var listener: ListenerRegistration? = null

    /** Format today's date as yyyy-MM-dd (e.g. 2025-08-23) */
    private fun todayDate(): String =
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

    /** Start realtime listener for today's booked slots */
    fun startListeningToday() {
        stopListening()
        val doctorId = uid ?: return
        val date = todayDate()

        listener = firestore.collection("appointments")
            .document(doctorId)
            .collection(date)
            .orderBy("bookedTime", Query.Direction.ASCENDING) // stable order
            .addSnapshotListener { snap, e ->
                if (e != null) {
                    _appointments.value = emptyList()
                    return@addSnapshotListener
                }
                val list = snap?.documents?.mapNotNull { it.toObject(BookedSlots::class.java) }
                    ?: emptyList()
                _appointments.value = list
            }
    }

    /** If you ever need a one-shot refresh instead of realtime */
    fun loadTodayOnce() = viewModelScope.launch {
        val doctorId = uid ?: return@launch
        val date = todayDate()

        val snap = firestore.collection("appointments")
            .document(doctorId)
            .collection(date)
            .orderBy("bookedTime", Query.Direction.ASCENDING)
            .get()
            .await()

        _appointments.value = snap.documents.mapNotNull { it.toObject(BookedSlots::class.java) }
    }

    fun stopListening() {
        listener?.remove()
        listener = null
    }

    override fun onCleared() {
        super.onCleared()
        stopListening()
    }
}
