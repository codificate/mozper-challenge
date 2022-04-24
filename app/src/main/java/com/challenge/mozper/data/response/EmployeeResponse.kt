package com.challenge.mozper.data.response

import com.challenge.mozper.domain.Employee

data class EmployeeResponse(
    val description: String,
    val firstName: String,
    val id: Int,
    val image: String,
    val lastName: String,
    val rating: Double
)

fun EmployeeResponse.toEmployee(): Employee {
    return Employee(
        description = description,
        firstName = firstName,
        id = id,
        image = image,
        lastName = lastName,
        rating = rating
    )
}