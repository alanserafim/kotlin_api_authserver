package br.dev.authserver.users

import br.dev.authserver.roles.Role
import br.dev.authserver.roles.RoleRepository
import jakarta.transaction.Transactional
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PostMapping

@Service
class UserService(
    var repository: UserRepository,
    var roleRepository: RoleRepository
) {
    fun insert(user: User): User?  {
        if(repository.findByEmail(user.email) != null) {
          return null;
        }
        return repository.save(user);
    }

    fun findAll(sortDir: SortDir) = when(sortDir) {
        SortDir.ASC -> repository.findAll(Sort.by("name").ascending())
        SortDir.DESC -> repository.findAll(Sort.by("name").descending())
    }

    fun findByIdOrNull(id: Long) = repository.findByIdOrNull(id)

    fun delete(id: Long): Boolean {
        val user = repository.findByIdOrNull(id) ?: return false
        repository.delete(user)
        return true;
    }

    @Transactional
    fun addRole(id: Long, roleName: String): Boolean? {
        val user = repository.findByIdOrNull(id) ?: return null
        if(user.roles.any { it.name == roleName }) return false

        val role = roleRepository.findByName(roleName) ?: return null
        user.roles.add(role)
        repository.save(user)
        return true;
    }




}