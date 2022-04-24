package com.challenge.mozper.domain.use_cases

import com.challenge.mozper.base.DataResource
import com.challenge.mozper.domain.model.User
import com.challenge.mozper.domain.repository.LoginDaoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.sql.SQLException
import javax.inject.Inject

class CheckUserCredentialUseCase @Inject constructor(private val daoRepository: LoginDaoRepository) {

    operator fun invoke(): Flow<DataResource<User>> = flow {
        try {
            emit(DataResource.Loading())
            val data = daoRepository.getUserSession()
            data?.let { emit(DataResource.Success(it)) }
                ?: kotlin.run { emit(DataResource.Error("Couldn't fetch user info from database")) }
        } catch (e: Exception) {
            emit(DataResource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: SQLException) {
            emit(DataResource.Error(e.localizedMessage ?: "Couldn't fetch user info from database"))
        }
    }

}