package com.flashcards.domain.invite

import com.flashcards.domain.model.DeckRole
import com.flashcards.domain.support.*
import jakarta.persistence.*
import java.time.Instant
import java.util.UUID

@Entity @Table(name = "invites")
class Invite(
    @Id @Convert(converter = UuidBinaryConverter::class)
    @Column(columnDefinition = "BINARY(16)")
    var id: UUID = UuidV7.new(),

    @Column(name = "deck_id", columnDefinition = "BINARY(16)", nullable = false) var deckId: UUID,
    @Column(name = "inviter_id", columnDefinition = "BINARY(16)", nullable = false) var inviterId: UUID,
    @Column(nullable = false) var inviteeEmail: String,

    @Enumerated(EnumType.STRING) @Column(nullable = false) var role: DeckRole = DeckRole.EDITOR,
    @Column(nullable = false, unique = true) var token: String,
    @Column(nullable = false) var expiresAt: Instant,
    var acceptedAt: Instant? = null
) : BaseTimeEntity()