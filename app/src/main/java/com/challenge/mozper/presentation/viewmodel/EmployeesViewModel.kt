package com.challenge.mozper.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.mozper.base.DataResource
import com.challenge.mozper.domain.use_cases.FetchEmployeesFromApiUseCase
import com.challenge.mozper.presentation.states.EmployeeListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class EmployeesViewModel @Inject constructor(private val fetchEmployeesApiUseCase: FetchEmployeesFromApiUseCase) :
    ViewModel() {

    private val _mutableEmployeesState = MutableLiveData<EmployeeListState>(EmployeeListState())
    val employeesState: LiveData<EmployeeListState> = _mutableEmployeesState

    init {
        fetchEmployeesFromApi()
    }

    private fun fetchEmployeesFromApi() {
        fetchEmployeesApiUseCase().onEach { result ->
            when (result) {
                is DataResource.Error -> {
                    _mutableEmployeesState.postValue(EmployeeListState(error = result.message ?: "An unexpected error occurred"))
                }
                is DataResource.Loading -> {
                    _mutableEmployeesState.postValue(EmployeeListState(isLoading = true))
                }
                is DataResource.Success -> {
                    _mutableEmployeesState.postValue(EmployeeListState(employees = result.data))
                }
            }
        }.launchIn(viewModelScope)
    }

}