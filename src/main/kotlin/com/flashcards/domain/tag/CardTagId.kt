// com/flashcards/domain/tag/CardTag.kt
package com.flashcards.domain.tag

import jakarta.persistence.*
import java.io.Serializable
import java.util.UUID

@Embeddable
data class CardTagId(
    @Column(name = "card_id", columnDefinition = "BINARY(16)") var cardId: UUID? = null,
    @Column(name = "tag_id",  columnDefinition = "BINARY(16)") var tagId:  UUID? = null
) : Serializable

@Entity @Table(name = "card_tags")
class CardTag(@EmbeddedId var id: CardTagId = CardTagId())
