package com.flashcards.domain.card

import com.flashcards.domain.deck.Deck
import com.flashcards.domain.support.BaseTimeEntity
import com.flashcards.domain.support.UuidBinaryConverter
import com.flashcards.domain.support.UuidV7
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.Instant
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
 * - 카드 정렬: sortOrder 로 사용자 정의 순서 관리
 * - 소프트 삭제: deletedAt 으로 복구 가능 상태 유지
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
    var difficultyEst: Double? = null,   // 난이도 추정 (학습 알고리즘에 활용)

    @Column(nullable = false)
    var sortOrder: Int = 0,   // 카드 정렬 순서

    @Column
    var deletedAt: Instant? = null   // 소프트 삭제 시각
) : BaseTimeEntity()   // 카드 생성/수정 시간 추적
