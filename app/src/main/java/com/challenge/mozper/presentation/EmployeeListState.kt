package com.challenge.mozper.presentation

import com.challenge.mozper.domain.Employee

data class EmployeeListState(
    val isLoading: Boolean = false,
    val employees: List<Employee>? = emptyList(),
    val error: String = ""
)
