package br.dev.authserver

import br.dev.authserver.roles.Role
import br.dev.authserver.roles.RoleRepository
import br.dev.authserver.users.User
import br.dev.authserver.users.UserRepository
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component

@Component
class Bootstrapper(
    val userRepository: UserRepository,
    val roleRepository: RoleRepository
) : ApplicationListener<ContextRefreshedEvent> {
    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        val adminRole = roleRepository.findByName("ADMIN") ?:
            roleRepository
                .save(Role(name ="ADMIN", description = "System Administrator"))
        roleRepository.findByName("USER") ?:
            roleRepository.save(Role(name ="USER", description = "System User"))

        if(userRepository.findByRole("ADMIN").isEmpty()) {
            val admin = User(
                name = "admin",
                email= "admin@email.com",
                password = "admin",
                )
            admin.roles.add(adminRole)
            userRepository.save(admin)
        }

    }



}