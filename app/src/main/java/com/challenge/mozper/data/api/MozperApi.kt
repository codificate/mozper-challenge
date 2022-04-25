package com.challenge.mozper.data.api

import com.challenge.mozper.data.response.EmployeesResponse
import retrofit2.http.GET

interface MozperApi {

    @GET("/")
    suspend fun getEmployees(): EmployeesResponse

}