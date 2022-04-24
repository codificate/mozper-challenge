package com.challenge.mozper.domain.use_cases

import com.challenge.mozper.base.DataResource
import com.challenge.mozper.domain.model.Employee
import com.challenge.mozper.domain.repository.MozperDaoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import java.sql.SQLException
import javax.inject.Inject

class FetchEmployeesDaoUseCase @Inject constructor(private val daoRepository: MozperDaoRepository){

    operator fun invoke() : Flow<DataResource<List<Employee>>> = flow {
        try {
            emit(DataResource.Loading())
            val data = daoRepository.getAllEmployees()
            emit(DataResource.Success(data))
        } catch (e: Exception) {
            emit(DataResource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: SQLException) {
            emit(DataResource.Error(e.localizedMessage ?: "Couldn't fetch info from database"))
        }
    }

}