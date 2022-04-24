package com.challenge.mozper.domain.repository

import com.challenge.mozper.data.response.EmployeesResponse

interface MozperApiRepository {

    suspend fun fetchEmployees(): EmployeesResponse

}