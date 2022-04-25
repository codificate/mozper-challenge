package com.challenge.mozper.presentation.employees

import androidx.recyclerview.widget.RecyclerView
import com.challenge.mozper.databinding.EmployeeRowBinding
import com.challenge.mozper.domain.model.Employee
import com.challenge.mozper.util.downloadImage

class EmployeeItemViewHolder(private val binding: EmployeeRowBinding, private val listener: EmployeeItemListener):
    RecyclerView.ViewHolder(binding.root){

        fun bind(employee: Employee) {
            binding.employee = employee
            employee.image?.takeIf { it.isNotEmpty() }.let { avatar ->
                avatar?.let {
                    downloadImage(it, binding.root.context)
                        .into(binding.employeeImage)
                }
            }
            binding.employeeItemContainer.setOnClickListener {
                listener.onEmployeeClicked(employee)
            }
        }

}