package br.dev.authserver.users

import br.dev.authserver.exceptions.UnsupportedMediaTypeException
import br.dev.authserver.files.FileStorage
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class AvatarService(
    @Qualifier("fileStorage") private val storage: FileStorage,
) {
    fun save(user: User, avatar: MultipartFile): String {
        try {
            val extension = when (avatar.contentType) {
                "image/jpeg" -> "jpg"
                "image/jpg" -> "jpg"
                "image/png" -> "png"
                else -> throw UnsupportedMediaTypeException("jpg", "png")
            }
            val path = "${user.id}/a_${user.id}.$extension"
            storage.save(user, "$ROOT/$path", avatar)
            return "${user.id}/xl_a_${user.id}.png"
        } catch (exception: Error) {
            log.warn("Could not save user ${user.id} avatar ${avatar.originalFilename}: ${exception.message}")
            return DEFAULT_AVATAR
        }
    }

    fun urlFor(path: String) = storage.urlFor("$ROOT/$path")

    companion object {
        const val ROOT = "avatars"
        const val DEFAULT_AVATAR = "default.png"
        private val log = LoggerFactory.getLogger(AvatarService::class.java)
    }
}