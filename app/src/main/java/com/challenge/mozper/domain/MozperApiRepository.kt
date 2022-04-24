package com.challenge.mozper.domain

import com.challenge.mozper.data.response.EmployeesResponse

interface MozperApiRepository {

    suspend fun fetchEmployees(): EmployeesResponse

}