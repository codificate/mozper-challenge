package com.challenge.mozper.presentation.states

import com.challenge.mozper.domain.model.Employee

data class EmployeeListState(
    val isLoading: Boolean = false,
    val employees: List<Employee>? = emptyList(),
    val error: String = ""
)
