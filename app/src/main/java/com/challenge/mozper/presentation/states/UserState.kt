package com.challenge.mozper.presentation.states

import com.challenge.mozper.domain.model.User

data class UserState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val error: String = ""
)
