package com.emctechlab.demo.repository

import com.emctechlab.demo.model.Sender
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Created by Sunil Mishra on 6/20/18
 */
interface SenderRepository : MongoRepository<Sender, String>