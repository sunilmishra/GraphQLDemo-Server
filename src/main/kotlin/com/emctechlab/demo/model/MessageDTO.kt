package com.emctechlab.demo.model

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

/**
 * Created by Sunil Mishra on 6/10/18
 */
@Document(collection = "collection_emessage")
@JsonInclude(JsonInclude.Include.NON_NULL)
class EMessage {
    @Id
    val id: String? = null
    var subject: String? = null
    var description: String? = null
    var category: String? = null
    var read: Boolean = false
    var sentDateTime: String = Date().toString()
    var senderEmail: String? = null

    @Transient
    var sender: Sender? = null

    override fun toString(): String {
        return "EMessage(id=$id, subject=$subject, description=$description, category=$category, read=$read, " +
                "sentDateTime=$sentDateTime, senderEmail=$senderEmail)"
    }
}

@Document(collection = "collection_sender")
@JsonInclude(JsonInclude.Include.NON_NULL)
class Sender {
    @Id
    var email: String? = null
    var firstName: String? = null
    var lastName: String? = null

    override fun toString(): String {
        return "Sender(email=$email, firstName=$firstName, lastName=$lastName)"
    }
}


