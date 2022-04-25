package com.challenge.mozper.presentation.employees

import com.challenge.mozper.domain.model.Employee

interface EmployeeItemListener {

    fun onEmployeeClicked(employee: Employee)

}