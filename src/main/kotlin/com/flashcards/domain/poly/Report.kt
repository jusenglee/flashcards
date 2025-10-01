package com.flashcards.domain.poly


import com.flashcards.domain.model.OwnerType
import com.flashcards.domain.support.*
import jakarta.persistence.*
import java.util.UUID

@Entity @Table(name = "reports")
class Report(
    @Id @Convert(converter = UuidBinaryConverter::class)
    @Column(columnDefinition = "BINARY(16)")
    var id: UUID = UuidV7.new(),

    @Enumerated(EnumType.STRING) @Column(name = "owner_type", nullable = false)
    var ownerType: OwnerType,

    @Column(name = "owner_id", columnDefinition = "BINARY(16)", nullable = false)
    var ownerId: UUID,

    @Column(nullable = false) var reason: String,
    @Column(nullable = false) var status: String = "open",

    @Column(name = "reporter_id", columnDefinition = "BINARY(16)") var reporterId: UUID? = null
) : BaseTimeEntity()