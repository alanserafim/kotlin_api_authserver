package br.dev.authserver.users

import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PostMapping

@Service
class UserService(
    var repository: UserRepository
) {
    fun insert(user: User) = repository.save(user)

    fun findAll(sortDir: SortDir) = repository.findAll(sortDir)

    fun findByIdOrNull(id: Long) = repository.findByIdOrNull(id)

    fun delete(id: Long) = repository.delete(id)



}