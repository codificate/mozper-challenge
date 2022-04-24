package com.challenge.mozper.domain.use_cases

import com.challenge.mozper.base.DataResource
import com.challenge.mozper.data.response.toEmployee
import com.challenge.mozper.domain.repository.MozperApiRepository
import com.challenge.mozper.domain.model.Employee
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FetchEmployeesFromApiUseCase @Inject constructor(private val apiRepository: MozperApiRepository){

    operator fun invoke() : Flow<DataResource<List<Employee>>> = flow {
        try {
            emit(DataResource.Loading())
            val data = apiRepository.fetchEmployees().employees.map { it.toEmployee() }
            emit(DataResource.Success(data))
        } catch(e: HttpException) {
            emit(DataResource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch(e: IOException) {
            emit(DataResource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

}