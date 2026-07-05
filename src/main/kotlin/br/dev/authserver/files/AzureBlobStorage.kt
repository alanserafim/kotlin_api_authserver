package br.dev.authserver.files

import br.dev.authserver.users.User
import com.azure.storage.blob.BlobServiceClient
import com.azure.storage.blob.BlobServiceClientBuilder
import com.azure.storage.blob.models.BlobHttpHeaders
import com.azure.storage.blob.options.BlobParallelUploadOptions
import org.springframework.core.io.InputStreamResource
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class AzureBlobStorage : FileStorage {
    private val blobServiceClient: BlobServiceClient = BlobServiceClientBuilder()
        .connectionString(System.getenv("AZURE_STORAGE_CONNECTION_STRING"))
        .buildClient()

    override fun save(
        user: User,
        path: String,
        file: MultipartFile
    ) {
        val containerClient = blobServiceClient.getBlobContainerClient(THUMB)
        val blobClient = containerClient.getBlobClient(path)
        val contentType = file.contentType ?: "application/octet-stream"
        val headers = BlobHttpHeaders().setContentType(contentType)
        val metadata = mapOf(
            "userId" to "${user.id}",
            "originalFileName" to (file.originalFilename ?: "")
        )

        val options = BlobParallelUploadOptions(
            file.inputStream,
            file.size)
            .setHeaders(headers)
            .setMetadata(metadata)
        blobClient.uploadWithResponse(
            options,
            null,
            null)
    }

    override fun load(path: String): Resource? {
        val containerClient = blobServiceClient.getBlobContainerClient(PUBLIC)
        val blobClient = containerClient.getBlobClient(
            path.replace("---", "/"),
        )
        return InputStreamResource(blobClient.openInputStream())
    }

    override fun urlFor(name: String): String = "${PREFIX}/$name"

    companion object {
        const val THUMB = "private"
        const val PUBLIC = "public"
        const val PREFIX = "https://cinetrackdataserver.blob.core.windows.net/public"
    }

}