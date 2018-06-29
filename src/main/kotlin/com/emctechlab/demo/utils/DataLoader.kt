package com.emctechlab.demo.utils

import com.emctechlab.demo.model.EMessage
import com.emctechlab.demo.model.Sender
import com.emctechlab.demo.repository.EMessageRepository
import com.emctechlab.demo.repository.SenderRepository
import java.util.*

/**
 * Created by Sunil Mishra on 6/14/18
 */
class DataLoader {

    fun loadDataIntoMongoDatabase(eMessageRepository: EMessageRepository, senderRepository: SenderRepository) {
        eMessageRepository.deleteAll()
        senderRepository.deleteAll()

        val listOfSender: MutableList<Sender> = mutableListOf()
        for (i in 1..4) {
            val sender = Sender()
            sender.email = "abc_$i@gmail.com"
            sender.firstName = "FirstName - $i"
            sender.lastName = "LastName - $i"
            listOfSender.add(sender)
        }
        senderRepository.saveAll(listOfSender)


        val listOfMessage: MutableList<EMessage> = mutableListOf()

        val categoryList = arrayOf("primary", "social", "promotions", "spam")

        for (i in 1..16) {
            val isRead = i % 3 == 0
            val category = categoryList[i % 4]

            val eMessage = EMessage()
            eMessage.subject = "Message - $i from GraphQL"
            eMessage.description = "$i - GraphQL is a query language for APIs and a runtime for" +
                    " fulfilling those queries with your existing data."
            eMessage.category = category
            eMessage.read = isRead
            eMessage.sentDateTime = Date().toString()
            eMessage.senderEmail = listOfSender[i%4].email

            listOfMessage.add(eMessage)
        }
        eMessageRepository.saveAll(listOfMessage)
        println("Message List Saved: ${eMessageRepository.findAll()}")
    }
}