package com.flashcards.domain.deck

import com.flashcards.domain.model.DeckRole
import com.flashcards.domain.support.BaseTimeEntity
import com.flashcards.domain.user.User
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import jakarta.persistence.Table
import java.io.Serializable
import java.time.Instant
import java.util.UUID

/**
 * 덱 멤버 복합키
 * - deckId: 대상 덱 ID
 * - userId: 참여자 ID
 */
@Embeddable
data class DeckMemberId(
    @Column(name = "deck_id", columnDefinition = "BINARY(16)") var deckId: UUID? = null,
    @Column(name = "user_id", columnDefinition = "BINARY(16)") var userId: UUID? = null
) : Serializable

/**
 * 덱 멤버 엔티티
 *
 * 기능:
 * - 협업 관리: 덱 초대 수락 후 멤버와 역할(Owner/Editor/Viewer) 관리
 * - 권한 제어: DeckRole을 통해 덱 단위 접근 범위를 제어
 * - 활동 추적: acceptedAt 으로 참여 시점을 기록해 감사/통계에 활용
 */
@Entity
@Table(name = "deck_members")
class DeckMember(
    @EmbeddedId var id: DeckMemberId = DeckMemberId(),

    @MapsId("deckId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deck_id", nullable = false)
    var deck: Deck,

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var role: DeckRole = DeckRole.VIEWER,

    @Column
    var acceptedAt: Instant? = null
) : BaseTimeEntity()
