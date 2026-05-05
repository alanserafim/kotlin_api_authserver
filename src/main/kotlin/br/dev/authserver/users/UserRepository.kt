package br.dev.authserver.users

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?

}


/*
class UserRepository {
    private val users = mutableMapOf<Long, User>()

    fun save(user: User): User {
        if(user.id == null){
            user.id = (users.keys.maxOrNull()?: 0) + 1;
        }
        users[user.id!!] = user;
        return user;
    }

    fun findAll(sortDir: SortDir) =
        if(sortDir == SortDir.ASC) users.values.sortedBy { it.name }
        else users.values.sortedByDescending { it.name }

    fun findByIdOrNull(id: Long) = users[id]

    fun delete(id: Long) = users.remove(id) != null
}
*/