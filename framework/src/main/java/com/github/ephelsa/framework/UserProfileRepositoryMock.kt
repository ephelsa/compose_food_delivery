package com.github.ephelsa.framework

import com.github.ephelsa.data.UserProfileRepository
import com.github.ephelsa.domain.LocationInformation
import com.github.ephelsa.domain.PhoneInformation
import com.github.ephelsa.domain.UserInformation
import com.github.ephelsa.framework.di.IODispatcher
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@Singleton
class UserProfileRepositoryMock @Inject constructor(
    @IODispatcher override val coroutineContext: CoroutineContext
) : UserProfileRepository, CoroutineScope {

    override suspend fun getUserInformation(): UserInformation = withContext(coroutineContext) {
        delay(2_000L)

        UserInformation(
            image = "https://avatars.githubusercontent.com/u/25624035?v=4",
            firstName = "Leonardo",
            secondName = "Andrés",
            firstSurname = "Pérez",
            secondSurname = "Castilla",
            age = 24,
            locationInformation = LocationInformation(
                city = "Sincelejo",
                state = "Sucre",
                country = "Colombia",
                address = "123 siempre viva",
                extras = listOf("Urbanización", "Barrio Las Palmas"),
            ),
            phoneInformation = PhoneInformation(
                indicative = "+57",
                number = 3505146661
            )
        )
    }

    override suspend fun getUserThumbnail(): String = withContext(coroutineContext) {
        delay(500L)

        "https://avatars.githubusercontent.com/u/25624035?v=4"
    }
}