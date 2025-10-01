package com.flashcards.domain.deck

import com.flashcards.domain.model.DeckVisibility
import com.flashcards.domain.support.BaseTimeEntity
import com.flashcards.domain.support.UuidBinaryConverter
import com.flashcards.domain.support.UuidV7
import com.flashcards.domain.user.User
import jakarta.persistence.*
import java.util.UUID

/**
 * 덱 엔티티
 *
 * 기능:
 * - 덱 생성/수정/삭제 (Soft Delete 지원 예정)
 * - 덱 공유 범위 관리:
 *   - PRIVATE: 본인만 접근
 *   - LINK: 토큰 URL로 접근 가능
 *   - PUBLIC: 전체 공개
 * - 덱 메타정보: title, description, coverImageUrl (추가 예정)
 * - 덱 통계: cardCount, lastActivityAt (추가 예정)
 * - 덱 협업: DeckMember와 연관되어 멤버 초대/권한 관리
 *
 * TODO: 다음 필드들 추가 예정
 * - description: String?
 * - coverImageUrl: String?
 * - cardCount: Int (계산된 값 또는 캐싱)
 * - lastActivityAt: Instant?
 * - deletedAt: Instant? (Soft Delete)
 */
@Entity
@Table(name = "decks")
class Deck (
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
) : BaseTimeEntity()   // createdAt, updatedAt 자동 관리