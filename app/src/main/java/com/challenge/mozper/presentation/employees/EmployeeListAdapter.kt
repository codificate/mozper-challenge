package com.challenge.mozper.presentation.employees

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.challenge.mozper.databinding.EmployeeRowBinding
import com.challenge.mozper.domain.model.Employee

class EmployeeListAdapter(private val listener: EmployeeItemListener) : RecyclerView.Adapter<EmployeeItemViewHolder>(){

    private val employeeList = mutableListOf<Employee>()

    @SuppressLint("NotifyDataSetChanged")
    fun setEmployeesList(employees: List<Employee>) {
        employeeList.removeAll(employeeList)
        employees.sortedBy { employee -> employee.firstName }.let {
            employeeList.addAll(employees)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeItemViewHolder {
        val binding = EmployeeRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmployeeItemViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: EmployeeItemViewHolder, position: Int) {
        holder.bind(employeeList[position])
    }

    override fun getItemCount() = employeeList.size

}