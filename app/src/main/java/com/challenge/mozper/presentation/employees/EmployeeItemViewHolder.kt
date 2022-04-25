package com.challenge.mozper.presentation.employees

import androidx.recyclerview.widget.RecyclerView
import com.challenge.mozper.databinding.EmployeeRowBinding
import com.challenge.mozper.domain.model.Employee

class EmployeeItemViewHolder(private val binding: EmployeeRowBinding, private val listener: EmployeeItemListener):
    RecyclerView.ViewHolder(binding.root){

        fun bind(employee: Employee) {
            binding.employee = employee
            binding.employeeItemContainer.setOnClickListener {
                listener.onEmployeeClicked(employee)
            }
        }

}