// com/flashcards/domain/card/CardRevision.kt
package com.flashcards.domain.card

import com.flashcards.domain.support.*
import com.flashcards.domain.user.User
import jakarta.persistence.*
import java.util.UUID

@Entity @Table(
    name = "card_revisions",
    indexes = [Index(name = "idx_card_rev_card_time", columnList = "card_id, created_at DESC")]
)
class CardRevision(
    @Id @Convert(converter = UuidBinaryConverter::class)
    @Column(columnDefinition = "BINARY(16)")
    var id: UUID = UuidV7.new(),

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "card_id", nullable = false)
    var card: Card,

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "editor_id", nullable = false)
    var editor: User,

    // JSON Patch 저장 (MySQL JSON)
    @Column(name = "diff_blob", columnDefinition = "json", nullable = false)
    var diffJson: String
) : BaseTimeEntity()
