package com.flashcards.domain.study

import com.flashcards.domain.model.StudyGrade
import com.flashcards.domain.support.BaseTimeEntity
import com.flashcards.domain.support.UuidBinaryConverter
import com.flashcards.domain.support.UuidV7
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table
import java.util.UUID

/**
 * 학습 이벤트 엔티티
 *
 * 기능:
 * - 학습 세션 기록: 사용자/카드/세션 단위로 grade, timeMs 저장
 * - 통계 제공: createdAt 인덱스로 최근 학습 히스토리 조회
 * - 학습 알고리즘 입력: StudyState 업데이트를 위한 근거 데이터 제공
 */
@Entity
@Table(
    name = "study_events",
    indexes = [Index(name = "idx_events_user_time", columnList = "user_id, created_at DESC")]
)
class StudyEvent(
    @Id @Convert(converter = UuidBinaryConverter::class)
    @Column(columnDefinition = "BINARY(16)")
    var id: UUID = UuidV7.new(),

    @Column(name = "user_id", columnDefinition = "BINARY(16)", nullable = false) var userId: UUID,
    @Column(name = "card_id", columnDefinition = "BINARY(16)", nullable = false) var cardId: UUID,
    @Column(name = "session_id", columnDefinition = "BINARY(16)") var sessionId: UUID? = null,

    @Enumerated(EnumType.STRING) @Column(nullable = false) var grade: StudyGrade,
    @Column(nullable = false) var timeMs: Int
) : BaseTimeEntity()
