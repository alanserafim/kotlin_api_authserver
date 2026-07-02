package br.dev.authserver.users.responses

import br.dev.authserver.users.User

data class UserResponse(
    val id: Long,
    val name: String,
    val bio: String,
    val email: String,
    //val avatar: String,
){
    constructor(
        user: User,
        //avatarUrl: String
    ) : this(
        user.id!!,
        user.name,
        user.bio,
        user.email,
        //avatarUrl
    )
}
