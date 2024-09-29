package org.example.project.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MealItem(
    @SerialName("meals")
    val meals: List<MealX>
)