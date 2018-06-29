package com.emctechlab.demo.resolver

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.emctechlab.demo.model.EMessage
import com.emctechlab.demo.model.Sender
import com.emctechlab.demo.repository.EMessageRepository
import com.emctechlab.demo.repository.SenderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

/**
 * Created by Sunil Mishra on 6/15/18
 */
@Component
class Mutation : GraphQLMutationResolver {

    @Autowired
    lateinit var senderRepository: SenderRepository

    @Autowired
    lateinit var eMessageRepository: EMessageRepository

    fun createSender(email: String, firstName: String, lastName: String): Sender {
        val newSender = Sender()
        newSender.email = email
        newSender.firstName = firstName
        newSender.lastName = lastName
        return senderRepository.save(newSender)
    }

    fun createMessage(subject: String, description: String, category: String, senderEmail: String): String {
        val newMessage = EMessage()
        newMessage.subject = subject
        newMessage.description = description
        newMessage.category = category
        newMessage.read = false
        newMessage.sentDateTime = Date().toString()
        newMessage.senderEmail = senderEmail

        val message = eMessageRepository.save(newMessage)
        return message.id.toString()
    }
}