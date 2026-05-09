package br.dev.authserver.users

import br.dev.authserver.exceptions.BadRequestException
import br.dev.authserver.exceptions.NotFoundException
import br.dev.authserver.exceptions.UnauthorizedException
import br.dev.authserver.roles.Role
import br.dev.authserver.roles.RoleRepository
import br.dev.authserver.security.Jwt
import br.dev.authserver.users.responses.LoginResponse
import br.dev.authserver.users.responses.UserResponse
import jakarta.transaction.Transactional
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PostMapping

@Service
class UserService(
    val repository: UserRepository,
    val roleRepository: RoleRepository,
    val jwt: Jwt,
) {
    fun insert(user: User): User  {
        if(repository.findByEmail(user.email) != null) {
          throw BadRequestException("User already exists")
        }
        return repository.save(user)
            .also {  log.info("User {} added.", it.id) }
    }

    fun findAll(sortDir: SortDir) = when(sortDir) {
        SortDir.ASC -> repository.findAll(Sort.by("name").ascending())
        SortDir.DESC -> repository.findAll(Sort.by("name").descending())
    }

    fun findByRole(roleName: String) =
        repository.findByRole(roleName.uppercase())

    fun findByIdOrNull(id: Long) = repository.findByIdOrNull(id)

    fun findById(id: Long) = repository.findByIdOrNull(id) ?: throw NotFoundException(id)

    fun delete(id: Long) {
        val user = findById(id)
        if(user.isAdmin() && repository.findByRole("ADMIN").size == 1) {
            throw BadRequestException("Cannot delete the last admin!")
        }
        repository.delete(user)
        log.warn("User {} deleted.", id)
    }

    fun update(id: Long, name: String): User? {
        val user = findById(id)
        if(user.name == name) {
            return null
        }
        user.name = name
        repository.save(user)
        return user
    }

    @Transactional
    fun addRole(id: Long, roleName: String): Boolean {
        val upperRole = roleName.uppercase()
        val user = findById(id)
        if(user.roles.any { it.name == upperRole }) return false
        val role = roleRepository.findByName(upperRole) ?: throw BadRequestException("Role $upperRole not found")
        user.roles.add(role)
        repository.save(user)
        log.info("Role {} granted to user {}", upperRole, user.id)
        return true;
    }

    fun login(email: String, password: String): LoginResponse {
        val user = repository.findByEmail(email) ?: throw UnauthorizedException("Email or password invalid!")
        if (user.password != password) {
            throw UnauthorizedException("Email or password invalid!")
        }
        log.info("User {} logged in.", user.id)
        return LoginResponse(
            token = jwt.createToken(user),
            user = UserResponse(user)
        )
    }

    companion object {
        val log = LoggerFactory.getLogger(UserService::class.java)
    }




}