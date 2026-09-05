package pe.edu.unu.registrationform.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResultadoPerfil(
    val verificado: Boolean,
    val mensaje: String
) : Parcelable