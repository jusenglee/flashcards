// com/flashcards/domain/tag/CardTag.kt
package com.flashcards.domain.tag

import com.flashcards.domain.card.Card
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import jakarta.persistence.Table
import java.io.Serializable
import java.util.UUID

/**
 * 카드-태그 연결 복합키
 */
@Embeddable
data class CardTagId(
    @Column(name = "card_id", columnDefinition = "BINARY(16)") var cardId: UUID? = null,
    @Column(name = "tag_id", columnDefinition = "BINARY(16)") var tagId: UUID? = null
) : Serializable

/**
 * 카드-태그 연결 엔티티
 * - 카드와 태그를 다대다로 연결해 태그 기반 검색/필터를 지원
 */
@Entity
@Table(name = "card_tags")
class CardTag(
    @EmbeddedId var id: CardTagId = CardTagId(),

    @MapsId("cardId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    var card: Card,

    @MapsId("tagId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", nullable = false)
    var tag: Tag
)
