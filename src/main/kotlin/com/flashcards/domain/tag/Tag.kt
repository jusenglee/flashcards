// com/flashcards/domain/tag/Tag.kt
package com.flashcards.domain.tag

import com.flashcards.domain.support.*
import jakarta.persistence.*
import java.util.UUID

@Entity @Table(name = "tags")
class Tag(
    @Id @Convert(converter = UuidBinaryConverter::class)
    @Column(columnDefinition = "BINARY(16)")
    var id: UUID = UuidV7.new(),

    @Column(nullable = false, unique = true) var name: String
) : BaseTimeEntity()
