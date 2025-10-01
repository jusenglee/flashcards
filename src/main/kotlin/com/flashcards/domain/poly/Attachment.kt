package com.flashcards.domain.poly

import com.flashcards.domain.model.OwnerType
import com.flashcards.domain.support.*
import jakarta.persistence.*
import java.util.UUID

@Entity @Table(
    name = "attachments",
    indexes = [Index(name = "idx_attach_owner", columnList = "owner_type, owner_id")]
)
class Attachment(
    @Id @Convert(converter = UuidBinaryConverter::class)
    @Column(columnDefinition = "BINARY(16)")
    var id: UUID = UuidV7.new(),

    @Enumerated(EnumType.STRING) @Column(name = "owner_type", nullable = false)
    var ownerType: OwnerType,

    @Column(name = "owner_id", columnDefinition = "BINARY(16)", nullable = false)
    var ownerId: UUID,

    @Column(nullable = false) var uri: String,
    @Column var mime: String? = null,
    @Column var size: Long? = null
) : BaseTimeEntity()