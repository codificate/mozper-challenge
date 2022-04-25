package com.challenge.mozper.domain.use_cases

import com.challenge.mozper.domain.model.User
import com.challenge.mozper.domain.repository.LoginDaoRepository
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject

class SaveUserCredentialUseCase @Inject constructor(private val daoRepository: LoginDaoRepository) {

    operator fun invoke(email: String) = flow<Unit> {
        daoRepository.insert(User(email, Date()))
    }

}