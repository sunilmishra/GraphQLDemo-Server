package com.emctechlab.demo.resolver

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.emctechlab.demo.model.EMessage
import com.emctechlab.demo.model.Sender
import com.emctechlab.demo.repository.EMessageRepository
import com.emctechlab.demo.repository.SenderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Created by Sunil Mishra on 6/14/18
 */
@Component
class Query : GraphQLQueryResolver {

    @Autowired
    lateinit var senderRepository: SenderRepository

    @Autowired
    lateinit var eMessageRepository: EMessageRepository

    fun allMessage(): List<EMessage> {
        val senderList = senderRepository.findAll()
        val messageList: MutableList<EMessage> = mutableListOf()
        val allMessage = eMessageRepository.findAll()
        allMessage.forEach { message ->
            val sender = senderList.find { it.email == message.senderEmail }
            message.sender = sender
            messageList.add(message)
        }
        return messageList
    }

    fun message(id: String): EMessage {
        val eMessage = eMessageRepository.findById(id).get()
        val sender = senderRepository.findById(eMessage.senderEmail!!).get()
        eMessage.sender = sender
        return eMessage

    }

    fun allSender(): List<Sender> {
        return senderRepository.findAll()
    }
}