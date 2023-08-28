package com.example.plugin.serializable.custom

import io.ktor.server.http.*
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.Instant

object InstantSerializer : KSerializer<Instant> {


    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Instant", PrimitiveKind.STRING)
    override fun serialize(encoder: Encoder, value: Instant) {
        encoder.encodeString(value.toHttpDateString())
    }
    override fun deserialize(decoder: Decoder): Instant {
        val stringValue = decoder.decodeString()
        return Instant.parse(stringValue)
    }
}