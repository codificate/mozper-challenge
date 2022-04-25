package com.challenge.mozper.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.mozper.base.DataResource
import com.challenge.mozper.domain.model.Employee
import com.challenge.mozper.domain.use_cases.FetchEmployeesDaoUseCase
import com.challenge.mozper.domain.use_cases.FetchEmployeesFromApiUseCase
import com.challenge.mozper.domain.use_cases.SaveEmployeesOnDatabaseUseCase
import com.challenge.mozper.presentation.states.EmployeeListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class EmployeesViewModel @Inject constructor(
    private val fetchEmployeesApiUseCase: FetchEmployeesFromApiUseCase,
    private val fetchEmployeesDaoUseCase: FetchEmployeesDaoUseCase,
    private val saveEmployeesOnDatabaseUseCase: SaveEmployeesOnDatabaseUseCase
) :
    ViewModel() {

    private val _mutableEmployeesState = MutableLiveData<EmployeeListState>(EmployeeListState())
    val employeesState: LiveData<EmployeeListState> = _mutableEmployeesState

    private var employeeList: MutableList<Employee> = mutableListOf()

    init {
        fetchEmployeesFromApi()
    }

    fun findEmployeeBy(name: String) {
        employeeList.filter { employee -> employee.firstName.contentEquals(name, true) }.let { employeeMatches ->
            _mutableEmployeesState.postValue(EmployeeListState(employees = employeeMatches))
        }
    }

    private fun fetchEmployeesFromApi() {
        fetchEmployeesApiUseCase().onEach { result ->
            handleEmployeeData(result, result is DataResource.Error)
        }.launchIn(viewModelScope)
    }

    private fun fetchEmployeesFromDao() {
        fetchEmployeesDaoUseCase().onEach { result ->
            handleEmployeeData(result)
        }.launchIn(viewModelScope)
    }

    private fun handleEmployeeData(result: DataResource<List<Employee>>, apiErrorOCurred: Boolean = false) {
        when (result) {
            is DataResource.Error -> {
                if (apiErrorOCurred) {
                    fetchEmployeesFromDao()
                }
                postError(result)
            }
            is DataResource.Loading -> {
                _mutableEmployeesState.postValue(EmployeeListState(isLoading = true))
            }
            is DataResource.Success -> {
                result.data?.let { employeeList = it.toMutableList() }
                _mutableEmployeesState.postValue(EmployeeListState(employees = result.data))
                if (!apiErrorOCurred) { result.data?.let { saveEmployeesOnDatabaseUseCase(it) } }
            }
        }
    }

    private fun postError(result: DataResource<List<Employee>>) {
        _mutableEmployeesState.postValue(
            EmployeeListState(
                error = result.message ?: "An unexpected error occurred"
            )
        )
    }

}