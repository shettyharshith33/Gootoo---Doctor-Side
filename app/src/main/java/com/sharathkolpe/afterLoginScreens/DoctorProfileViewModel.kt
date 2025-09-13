package com.sharathkolpe.afterLoginScreens

import DayAvailability
import TimeSlot
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class DoctorProfileViewModel : ViewModel() {

    private val timeFormatter = DateTimeFormatter.ofPattern("hh:mm a")

    // State variables
    val name = MutableStateFlow("")
    val specialization = MutableStateFlow("")
    val qualification = MutableStateFlow("")
    val experience = MutableStateFlow("")
    val clinicName = MutableStateFlow("")
    val address = MutableStateFlow("")
    val city = MutableStateFlow("")
    val contact = MutableStateFlow("")

    private val _imageUri = MutableStateFlow<Uri?>(null)
    val imageUri: StateFlow<Uri?> = _imageUri

    private val _isUploading = MutableStateFlow(false)
    val isUploading: StateFlow<Boolean> = _isUploading

    // Store existing Firestore image URL
    private var existingImageUrl: String? = null

    private val _availabilityMap = MutableStateFlow(
        mapOf(
            "Monday" to DayAvailability(),
            "Tuesday" to DayAvailability(),
            "Wednesday" to DayAvailability(),
            "Thursday" to DayAvailability(),
            "Friday" to DayAvailability(),
            "Saturday" to DayAvailability(),
            "Sunday" to DayAvailability()
        )
    )
    val availabilityMap: StateFlow<Map<String, DayAvailability>> = _availabilityMap

    fun updateName(value: String) {
        name.value = value
    }

    fun updateSpecialization(value: String) {
        specialization.value = value
    }

    fun updateQualification(value: String) {
        qualification.value = value
    }

    fun updateExperience(value: String) {
        experience.value = value
    }

    fun updateClinicName(value: String) {
        clinicName.value = value
    }

    fun updateAddress(value: String) {
        address.value = value
    }

    fun updateCity(value: String) {
        city.value = value
    }

    fun updateContact(value: String) {
        contact.value = value
    }

    fun setImageUri(uri: Uri) {
        _imageUri.value = uri
    }

    fun loadDoctorProfile(onFailure: (String) -> Unit = {}) {
        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return

        viewModelScope.launch {
            try {
                val docSnapshot = FirebaseFirestore.getInstance()
                    .collection("doctors")
                    .document(uid)
                    .get()
                    .await()

                if (docSnapshot.exists()) {
                    name.value = docSnapshot.getString("name") ?: ""
                    specialization.value = docSnapshot.getString("specialization") ?: ""
                    qualification.value = docSnapshot.getString("qualification") ?: ""
                    experience.value = docSnapshot.getString("experience") ?: ""
                    clinicName.value = docSnapshot.getString("clinicName") ?: ""
                    address.value = docSnapshot.getString("address") ?: ""
                    city.value = docSnapshot.getString("city") ?: ""
                    contact.value = docSnapshot.getString("contact") ?: ""

                    // Load existing image URL
                    docSnapshot.getString("profileImageUrl")?.let { imageUrl ->
                        existingImageUrl = imageUrl
                        _imageUri.value = Uri.parse(imageUrl)
                    }

                    // Load availability
                    val availabilityData = docSnapshot.get("availability") as? Map<*, *>
                    val updatedMap = _availabilityMap.value.toMutableMap()

                    availabilityData?.forEach { (dayKey, dayValue) ->
                        val dayStr = dayKey as? String ?: return@forEach
                        val dayMap = dayValue as? Map<*, *> ?: return@forEach

                        val isAvailable = dayMap["isAvailable"] as? Boolean ?: false
                        val numberOfSlots = (dayMap["numberOfSlots"] as? Long)?.toInt() ?: 0

                        val slotsList = (dayMap["slots"] as? List<*>)?.mapNotNull { slotItem ->
                            val slotMap = slotItem as? Map<*, *> ?: return@mapNotNull null
                            val startTimeStr = slotMap["startTime"] as? String
                            val endTimeStr = slotMap["endTime"] as? String
                            TimeSlot(
                                startTime = startTimeStr?.let {
                                    LocalTime.parse(
                                        it,
                                        timeFormatter
                                    )
                                } as LocalTime,
                                endTime = endTimeStr?.let {
                                    LocalTime.parse(
                                        it,
                                        timeFormatter
                                    )
                                } as LocalTime
                            )
                        } ?: emptyList()

                        updatedMap[dayStr] = DayAvailability(
                            isAvailable = isAvailable,
                            numberOfSlots = numberOfSlots,
                            slots = slotsList
                        )
                    }

                    _availabilityMap.value = updatedMap
                }
            } catch (e: Exception) {
                onFailure(e.message ?: "Failed to load profile")
            }
        }
    }

    fun setDayAvailability(day: String, isAvailable: Boolean) {
        val updatedMap = _availabilityMap.value.toMutableMap()
        val current = updatedMap[day] ?: DayAvailability()
        updatedMap[day] = current.copy(isAvailable = isAvailable)
        _availabilityMap.value = updatedMap
    }

    fun updateSlotCount(day: String, count: Int) {
        val updatedMap = _availabilityMap.value.toMutableMap()
        val current = updatedMap[day] ?: DayAvailability()
        val updatedSlots = current.slots.toMutableList()

        while (updatedSlots.size < count) updatedSlots.add(TimeSlot())
        while (updatedSlots.size > count) updatedSlots.removeAt(updatedSlots.lastIndex)

        updatedMap[day] = current.copy(numberOfSlots = count, slots = updatedSlots)
        _availabilityMap.value = updatedMap
    }

    fun updateSlotTime(
        day: String,
        slotIndex: Int,
        startTime: LocalTime? = null,
        endTime: LocalTime? = null
    ) {
        val updatedMap = _availabilityMap.value.toMutableMap()
        val current = updatedMap[day] ?: return
        val updatedSlots = current.slots.toMutableList()

        if (slotIndex in updatedSlots.indices) {
            val existingSlot = updatedSlots[slotIndex]
            updatedSlots[slotIndex] = existingSlot.copy(
                startTime = startTime ?: existingSlot.startTime,
                endTime = endTime ?: existingSlot.endTime
            )
            updatedMap[day] = current.copy(slots = updatedSlots)
            _availabilityMap.value = updatedMap
        }
    }

    fun uploadDoctorProfile(onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return
        _isUploading.value = true

        viewModelScope.launch {
            try {
                var imageUrl = existingImageUrl // Default to old image

                // Only upload if it's a local file/content URI
                imageUri.value?.let { uri ->
                    if (uri.scheme == "content" || uri.scheme == "file") {
                        val ref =
                            FirebaseStorage.getInstance().reference.child("doctor_profiles/$uid.jpg")
                        ref.putFile(uri).await()
                        imageUrl = ref.downloadUrl.await().toString()
                    }
                }

                val availabilityForFirestore = availabilityMap.value.mapValues { (_, day) ->
                    mapOf(
                        "isAvailable" to day.isAvailable,
                        "numberOfSlots" to day.numberOfSlots,
                        "slots" to day.slots.map {
                            mapOf(
                                "startTime" to it.startTime?.format(timeFormatter),
                                "endTime" to it.endTime?.format(timeFormatter)
                            )
                        }
                    )
                }

                val doctorData = hashMapOf(
                    "name" to name.value,
                    "specialization" to specialization.value,
                    "qualification" to qualification.value,
                    "experience" to experience.value,
                    "clinicName" to clinicName.value,
                    "address" to address.value,
                    "city" to city.value,
                    "contact" to contact.value,
                    "profileImageUrl" to imageUrl,
                    "availability" to availabilityForFirestore
                )

                FirebaseFirestore.getInstance()
                    .collection("doctors")
                    .document(uid)
                    .set(doctorData)
                    .addOnSuccessListener {
                        _isUploading.value = false
                        onSuccess()
                    }
                    .addOnFailureListener {
                        _isUploading.value = false
                        onFailure(it.message ?: "Firestore error")
                    }

            } catch (e: Exception) {
                _isUploading.value = false
                onFailure(e.message ?: "Unexpected error")
            }
        }
    }
}

