package com.challenge.mozper.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.challenge.mozper.R
import com.challenge.mozper.databinding.FragmentEmployeeDetailBinding
import com.challenge.mozper.util.downloadImage
import com.challenge.mozper.util.downloadImageRoundCorners

class EmployeeDetailFragment : Fragment(R.layout.fragment_employee_detail) {

    private var _binding: FragmentEmployeeDetailBinding? = null
    private val binding by lazy { _binding!! }

    private val args: EmployeeDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmployeeDetailBinding.inflate(inflater, container, false)
        binding.employee = args.employee
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(args.employee) {
            image?.takeIf { it.isNotEmpty() }.let { avatar ->
                avatar?.let {
                    downloadImageRoundCorners(32, it, binding.root.context)
                        .into(binding.employeeAvatar)
                }
            }
            rating?.let {
                binding.employeeRating.setIsIndicator(true)
                binding.employeeRating.numStars = 5
                binding.employeeRating.max = 5
                binding.employeeRating.rating = it.toFloat()
            }
        }

    }
}