package br.dev.authserver.users

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
    val userService: UserService
) {

    @GetMapping("/ping")
    fun ping() = mapOf("status" to "ok");

    @PostMapping
    fun insert(@RequestBody user: User) = userService.insert(user);
}