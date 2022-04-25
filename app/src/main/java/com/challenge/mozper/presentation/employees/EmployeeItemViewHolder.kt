package com.challenge.mozper.presentation.employees

import androidx.recyclerview.widget.RecyclerView
import com.challenge.mozper.databinding.EmployeeRowBinding
import com.challenge.mozper.domain.model.Employee
import com.challenge.mozper.util.downloadImage
import com.challenge.mozper.util.downloadImageRoundCorners

class EmployeeItemViewHolder(private val binding: EmployeeRowBinding, private val listener: EmployeeItemListener):
    RecyclerView.ViewHolder(binding.root){

        fun bind(employee: Employee) {
            binding.employee = employee
            employee.image?.takeIf { it.isNotEmpty() }.let { avatar ->
                avatar?.let {
                    downloadImageRoundCorners(32, it, binding.root.context)
                        .into(binding.employeeImage)
                }
            }
            employee.rating?.let {
                binding.employeeRating.setIsIndicator(true)
                binding.employeeRating.numStars = 5
                binding.employeeRating.max = 5
                binding.employeeRating.rating = it.toFloat()
            }
            binding.employeeItemContainer.setOnClickListener {
                listener.onEmployeeClicked(employee)
            }
        }

}