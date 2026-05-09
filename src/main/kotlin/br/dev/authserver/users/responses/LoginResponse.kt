package br.dev.authserver.users.responses

data class LoginResponse(
    val token: String,
    val user: UserResponse
)
