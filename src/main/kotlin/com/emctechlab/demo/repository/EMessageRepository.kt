package com.emctechlab.demo.repository

import com.emctechlab.demo.model.EMessage
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Created by Sunil Mishra on 6/10/18
 */
interface EMessageRepository : MongoRepository<EMessage, String>