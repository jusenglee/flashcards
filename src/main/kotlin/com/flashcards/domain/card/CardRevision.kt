// com/flashcards/domain/card/CardRevision.kt
package com.flashcards.domain.card

import com.flashcards.domain.support.BaseTimeEntity
import com.flashcards.domain.support.UuidBinaryConverter
import com.flashcards.domain.support.UuidV7
import com.flashcards.domain.user.User
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.util.UUID

/**
 * 카드 수정 이력 엔티티
 *
 * 기능:
 * - 카드 수정 기록: JSON Patch 기반 diff 를 저장해 되돌리기/감사 용도로 활용
 * - 협업 감사: editor 정보를 통해 어떤 사용자가 수정했는지 추적
 * - 타임라인: createdAt 인덱스로 최신 수정 이력 조회 최적화
 */
@Entity
@Table(
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
