package com.challenge.mozper.presentation.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.challenge.mozper.R
import com.challenge.mozper.databinding.FragmentLoginBinding
import com.challenge.mozper.presentation.viewmodel.LoginViewModel
import com.challenge.mozper.util.isEmailValid
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment() : Fragment(R.layout.fragment_login), LoginScreenEventHandler {

    private var _binding: FragmentLoginBinding? = null
    private val binding by lazy { _binding!! }
    private val userViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.eventHandler = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel.userState.observe(viewLifecycleOwner, Observer { userState ->
            when {
                userState.user != null -> {

                }
                userState.isLoading -> {
                    // no-op
                }
                userState.error.isNotEmpty() -> showErrorToast(userState.error)
            }
        })
    }

    override fun onLoginClicked() {
        if (binding.loginEmail.toString().isEmpty() || binding.loginPassword.toString().isEmpty()) {
            showErrorToast("Credentials must not be empty")
        }

        if (!binding.loginEmail.toString().isEmailValid()) {
            binding.loginEmail.error = "Must be a valid email"
            showErrorToast("Must be a valid email")
        }

        userViewModel.saveUser(binding.loginEmail.toString())
    }

    private fun showErrorToast(error: String) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
    }
}