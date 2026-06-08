package br.dev.authserver.users

import br.dev.authserver.exceptions.ForbiddenException
import br.dev.authserver.security.UserToken
import br.dev.authserver.users.requests.CreateUserRequest
import br.dev.authserver.users.requests.LoginRequest
import br.dev.authserver.users.requests.UpdateUserRequest
import br.dev.authserver.users.responses.UserResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.authorization.SingleResultAuthorizationManager.permitAll
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.net.URI

@RestController
@RequestMapping("/users")
class UserController(
    val userService: UserService,
) {

    @GetMapping("/ping")
    fun ping() = mapOf("status" to "ok");

    @PostMapping
    fun insert(@RequestBody @Valid user: CreateUserRequest) =
        userService.insert(user.toUser())
            .let { UserResponse(it) }
            .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }

    @GetMapping
    fun list(
        @RequestParam sortDir: String?,
        @RequestParam role: String?
    ): ResponseEntity<List<UserResponse>> {
        val users = if (role != null) userService.findByRole(role)
                    else userService.findAll(SortDir.find(sortDir ?: "ASC"))
        return users
            .map { UserResponse(it) }
            .let { ResponseEntity.ok(it) }
    }

    @PostMapping("/login")
    fun login(
        @RequestBody @Valid user: LoginRequest
    ) = userService.login(user.email!!, user.password!!)

    @GetMapping("/{id}")
    fun getById(
        @PathVariable id: Long
    ) =
        userService.findById(id)
            .let { UserResponse(it) }
            .let { ResponseEntity.ok(it) }

    @SecurityRequirement(name = "jwt-auth")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable id: Long
    ) = userService.delete(id)

    @SecurityRequirement(name = "jwt-auth")
    @PreAuthorize("permitAll()")
    @PatchMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody @Valid user: UpdateUserRequest,
        auth: Authentication
    ): ResponseEntity<UserResponse> {
        val token = auth.principal as? UserToken ?: throw ForbiddenException()
        if (token.id != id && !token.isAdmin){
            throw ForbiddenException("Update is not allowed")
        }
        return userService.update(id, user.name)
            ?.let { UserResponse(it) }
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.noContent().build()
    }

    @SecurityRequirement(name = "jwt-auth")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/roles/{roleName}")
    fun grant(
        @PathVariable id: Long,
        @PathVariable roleName: String
    ): ResponseEntity<Void> =
        userService.addRole(id, roleName)
            .let { if(it) ResponseEntity.ok().build() else ResponseEntity.noContent().build() }

    @SecurityRequirement(name = "jwt-auth")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/avatar", consumes = ["multipart/form-data"])
    fun uploadAvatar(
        @PathVariable id: Long,
        @RequestParam avatar: MultipartFile
    ) = userService.saveAvatar(id, avatar)
        .let { ResponseEntity.created(URI(it)).build<Void>() }

}