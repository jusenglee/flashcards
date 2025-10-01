package com.flashcards.domain.deck

import com.flashcards.domain.model.DeckVisibility
import com.flashcards.domain.support.BaseTimeEntity
import com.flashcards.domain.support.UuidBinaryConverter
import com.flashcards.domain.support.UuidV7
import com.flashcards.domain.user.User
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.Instant
import java.util.UUID

/**
 * 덱 엔티티
 *
 * 기능:
 * - 덱 생성/수정/삭제 (deletedAt 기반 Soft Delete 지원)
 * - 덱 공유 범위 관리:
 *   - PRIVATE: 본인만 접근
 *   - LINK: 토큰 URL로 접근 가능
 *   - PUBLIC: 전체 공개
 * - 덱 메타정보: title, description, coverImageUrl
 * - 덱 통계: cardCount, lastActivityAt (카드 변경/학습과 연동)
 * - 덱 협업: DeckMember와 연관되어 멤버 초대/권한 관리
 * - 소프트 삭제: deletedAt 값으로 복구 가능 상태 유지
 */
@Entity
@Table(name = "decks", indexes = [Index(name = "idx_decks_owner", columnList = "owner_id")])
class Deck(
    @Id @Convert(converter = UuidBinaryConverter::class)
    @Column(columnDefinition = "BINARY(16)")
    var id: UUID = UuidV7.new(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    var owner: User,   // 덱 소유자 (Owner 권한)

    @Column(nullable = false)
    var title: String,   // 덱 제목 (검색 기능에 사용)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var visibility: DeckVisibility = DeckVisibility.PRIVATE,   // 덱 공유 범위

    @Column(columnDefinition = "text")
    var description: String? = null,   // 덱 설명 (검색/소개)

    @Column
    var coverImageUrl: String? = null,   // 덱 커버 이미지

    @Column(nullable = false)
    var cardCount: Int = 0,   // 덱에 포함된 카드 수 (캐싱 용도)

    @Column
    var lastActivityAt: Instant? = null,   // 마지막 카드/학습 활동 시각

    @Column
    var deletedAt: Instant? = null   // 소프트 삭제 시각
) : BaseTimeEntity()   // createdAt, updatedAt 자동 관리
