package com.flashcards.domain.card

import com.flashcards.domain.deck.Deck
import com.flashcards.domain.support.BaseTimeEntity
import com.flashcards.domain.support.UuidBinaryConverter
import com.flashcards.domain.support.UuidV7
import jakarta.persistence.*
import java.util.UUID

/**
 * 카드 엔티티
 * 
 * 기능:
 * - 카드 CRUD (생성/읽기/수정/삭제)
 * - 카드 필드: front(앞면), back(뒷면), difficultyEst(난이도 추정)
 * - 학습 기능: StudyState, StudyEvent와 연관되어 학습 진행 추적
 * - 검색 기능: front, back 텍스트 내용으로 검색
 * - 태그 관리: CardTag를 통해 태그와 다대다 관계
 * 
 * TODO: 다음 필드들 추가 예정
 * - sortOrder: Int (카드 정렬)
 * - deletedAt: Instant? (Soft Delete)
 * - CardRevision 연관: 카드 수정 이력 (JSON Patch 저장)
 * - Attachment 연관: 이미지/음성/첨부파일 (후순위)
 */
@Entity
@Table(name = "cards", indexes = [Index(name = "idx_cards_deck", columnList = "deck_id")])
class Card(
    @Id @Convert(converter = UuidBinaryConverter::class)
    @Column(columnDefinition = "BINARY(16)")
    var id: UUID = UuidV7.new(),

    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "deck_id", nullable = false)
    var deck: Deck,   // 소속 덱

    @Column(columnDefinition = "text", nullable = false) 
    var front: String,   // 카드 앞면 (검색 대상)

    @Column(columnDefinition = "text", nullable = false) 
    var back: String,   // 카드 뒷면 (검색 대상)

    @Column(name = "difficulty_est") 
    var difficultyEst: Double? = null   // 난이도 추정 (학습 알고리즘에 활용)
) : BaseTimeEntity()   // 카드 생성/수정 시간 추적