package br.dev.authserver.files

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
class FilesConfig {
    @Profile("aws")
    @Bean("fileStorage")
    fun s3Storage() = S3Storage()

    @Profile("azure")
    @Bean("fileStorage")
    fun azureBlobStorage() = AzureBlobStorage()

    @Profile("fs")
    @Bean("fileStorage")
    fun fsStorage() = FileSystemStorage()

}