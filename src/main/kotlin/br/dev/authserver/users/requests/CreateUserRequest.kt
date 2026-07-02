package br.dev.authserver.users.requests

import br.dev.authserver.users.User
import jakarta.annotation.Nonnull
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class CreateUserRequest(
    @NotBlank
    val name: String?,

    val bio: String?,

    @NotBlank
    @Email
    val email: String?,

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{8,}\$")
    val password: String?
){
    fun toUser(): User = User(
        name = name!!,
        email = email ?: "",
        password = password ?: "",
        bio = bio ?: "",
    )
}
