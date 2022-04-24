package com.challenge.mozper.data.repository

import com.challenge.mozper.data.api.MozperApi
import com.challenge.mozper.data.response.EmployeesResponse
import com.challenge.mozper.domain.MozperApiRepository
import javax.inject.Inject

class MozperApiRepositoryImpl @Inject constructor(private val api: MozperApi) :
    MozperApiRepository {

    override suspend fun fetchEmployees(): EmployeesResponse {
        return api.getEmployees()
    }

}