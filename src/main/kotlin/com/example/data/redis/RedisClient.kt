package com.example.data.redis

import com.example.property.AppProperties
import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPool

object RedisClient {
    var isConnected = false
    lateinit var jedis: Jedis

    fun init() {
        val jedisPool = JedisPool(AppProperties.redisProperty.host, AppProperties.redisProperty.port)
        jedis = jedisPool.resource
        try {
            val response = jedis.ping()
            isConnected = response == "PONG"
        } catch (e: Exception) {
            println("Error: ${e.message}")
        } finally {
            jedis.close()
            jedisPool.close()
        }
    }
}