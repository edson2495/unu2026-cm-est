package pe.edu.unu.registrationform.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Usuario(
    val nombre: String,
    val edad: Int,
    val correo: String
    ) : Parcelable