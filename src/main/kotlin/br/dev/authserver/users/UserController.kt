package br.dev.authserver.users

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
    val userService: UserService
) {

    @GetMapping("/ping")
    fun ping() = mapOf("status" to "ok");

    @PostMapping
    fun insert(@RequestBody user: User) =
        userService.insert(user)
            ?.let { ResponseEntity.status(HttpStatus.CREATED).body(it) }
            ?: ResponseEntity.badRequest().build()
        // outra forma de fazer //
        // ResponseEntity.status(HttpStatus.CREATED).body(userService.insert(user))

    @GetMapping
    fun list(@RequestParam sortDir: String?) =
        SortDir.findOrNull(sortDir ?: "ASC")
            ?.let { userService.findAll(it)}
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.badRequest().build()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) =
        userService.findByIdOrNull(id)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> =
        if (userService.delete(id)) ResponseEntity.ok().build()
        else ResponseEntity.notFound().build()

}