package br.dev.authserver.users

import br.dev.authserver.roles.Role
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import jakarta.persistence.Transient

@Entity
@Table(name = "userTable")
class User (
    @Id @GeneratedValue
    var id: Long? = null,

    @Column(nullable = false, unique = true)
    var email: String,

    @Column(nullable = false)
    var password: String = "",

    @Column(nullable = false)
    var name: String = "",

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "userRole",
        joinColumns = [JoinColumn(name = "idUser")],
        inverseJoinColumns = [JoinColumn(name = "idRole")]
    )
    var roles: MutableSet<Role> = mutableSetOf(),
    var avatar: String = AvatarService.DEFAULT_AVATAR,
) {
    @Transient
    fun isAdmin() = roles.any { r -> r.name == "ADMIN" }
}