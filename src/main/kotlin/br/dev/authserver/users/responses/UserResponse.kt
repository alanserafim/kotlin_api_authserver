package br.dev.authserver.users.responses

import br.dev.authserver.users.User

data class UserResponse(
    val id: Long,
    val name: String,
    val email: String,
){
    constructor(user: User) : this(user.id!!, user.name, user.email)
}
