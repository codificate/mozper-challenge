package com.challenge.mozper.presentation.employees

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.challenge.mozper.R
import com.challenge.mozper.databinding.FragmentEmployeeListBinding
import com.challenge.mozper.domain.model.Employee
import com.challenge.mozper.presentation.viewmodel.EmployeesViewModel
import com.challenge.mozper.util.hideKeyboard
import com.challenge.mozper.util.showErrorToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeeListFragment : Fragment(R.layout.fragment_employee_list), EmployeeItemListener {

    private var _binding: FragmentEmployeeListBinding? = null
    private val binding by lazy { _binding!! }
    private val employeesViewModel: EmployeesViewModel by viewModels()
    private val adapter = EmployeeListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmployeeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.employeeList.setHasFixedSize(true)
        binding.employeeList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.employeeList.adapter = adapter
        setSearchEmployeeListener()
        employeesViewModel.employeesState.observe(viewLifecycleOwner) { employeesState ->
            when {
                employeesState.employees?.isNotEmpty() == true -> employeesState.employees.let {
                    adapter.setEmployeesList(
                        it
                    )
                }
                employeesState.error.isNotEmpty() -> showErrorToast(employeesState.error)
            }
        }
    }

    override fun onEmployeeClicked(employee: Employee) {
        hideKeyboard()
        findNavController().navigate(EmployeeListFragmentDirections.actionEmployeesListFragmentToEmployeeDetailFragment(employee))
    }

    private fun setSearchEmployeeListener() {
        binding.searchEmployee.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, start: Int, before: Int, count: Int) {
                // no-op
            }

            override fun onTextChanged(p0: CharSequence?, start: Int, before: Int, count: Int) {
                // no-op
            }

            override fun afterTextChanged(editable: Editable?) {
                editable?.let {
                    if (it.length >= 3) {
                        employeesViewModel.findEmployeeBy(it.toString())
                    } else if (it.isEmpty()) {
                        employeesViewModel.findEmployeeBy("")
                    }
                }
            }
        })
    }
}