package com.flashcards.domain.deck

import com.flashcards.domain.model.DeckRole
import com.flashcards.domain.support.UuidBinaryConverter
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Embeddable
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import java.io.Serializable
import java.util.UUID

@Embeddable
data class DeckMemberId(
    @Column(name = "deck_id", columnDefinition = "BINARY(16)") var deckId: UUID? = null,
    @Column(name = "user_id", columnDefinition = "BINARY(16)") var userId: UUID? = null
) : Serializable

@Entity @Table(name = "deck_members")
class DeckMember(
    @EmbeddedId var id: DeckMemberId = DeckMemberId(),

    @Enumerated(EnumType.STRING) @Column(nullable = false)
    var role: DeckRole = DeckRole.VIEWER,
) {
    @Convert(converter = UuidBinaryConverter::class)
    fun deckId(): UUID? = id.deckId
    @Convert(converter = UuidBinaryConverter::class)
    fun userId(): UUID? = id.userId
}