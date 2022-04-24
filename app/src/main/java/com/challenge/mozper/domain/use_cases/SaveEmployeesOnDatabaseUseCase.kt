package com.challenge.mozper.domain.use_cases

import com.challenge.mozper.domain.model.Employee
import com.challenge.mozper.domain.repository.MozperDaoRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveEmployeesOnDatabaseUseCase @Inject constructor(private val daoRepository: MozperDaoRepository){

    operator fun invoke(employees: List<Employee>) {
        flow<Unit> {
            employees.forEach { daoRepository.insert(it) }
        }
    }

}