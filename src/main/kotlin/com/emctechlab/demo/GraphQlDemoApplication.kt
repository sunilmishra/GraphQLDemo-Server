package com.emctechlab.demo

import com.emctechlab.demo.repository.EMessageRepository
import com.emctechlab.demo.repository.SenderRepository
import com.emctechlab.demo.utils.DataLoader
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

/**
 * Created by Sunil Mishra on 6/10/18
 */
@SpringBootApplication
class GraphQlDemoApplication {

    @Bean
    fun initialize(repository: EMessageRepository, senderRepository: SenderRepository) = CommandLineRunner {
        DataLoader().loadDataIntoMongoDatabase(repository, senderRepository)
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(GraphQlDemoApplication::class.java, *args)
}







