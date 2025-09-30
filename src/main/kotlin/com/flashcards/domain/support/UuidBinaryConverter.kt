package com.flashcards.domain.support

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import java.nio.ByteBuffer
import java.util.UUID


@Converter(autoApply = true)
class UuidBinaryConverter : AttributeConverter<UUID, ByteArray> {
    override fun convertToDatabaseColumn(attr: UUID?): ByteArray? {
        if (attr == null) return null
        val bb = ByteBuffer.wrap(ByteArray(16))
        bb.putLong(attr.mostSignificantBits)
        bb.putLong(attr.leastSignificantBits)
        return bb.array()
    }

    override fun convertToEntityAttribute(p0: ByteArray?): UUID? {
        if (p0 == null) return null
        val bb = ByteBuffer.wrap(p0)
        return UUID(bb.long, bb.long)
    }
}