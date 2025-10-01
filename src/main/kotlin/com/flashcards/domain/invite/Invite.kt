package com.flashcards.domain.invite

import com.flashcards.domain.model.DeckRole
import com.flashcards.domain.support.BaseTimeEntity
import com.flashcards.domain.support.UuidBinaryConverter
import com.flashcards.domain.support.UuidV7
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant
import java.util.UUID

/**
 * 덱 초대 엔티티
 *
 * 기능:
 * - 초대 토큰 발급/만료 관리 (token, expiresAt)
 * - 초대 대상자 이메일 기반 협업 온보딩 (inviteeEmail)
 * - 역할 부여: DeckRole 로 초대 시 권한 지정
 * - acceptedAt 으로 초대 수락 여부 추적
 */
@Entity
@Table(name = "invites")
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
