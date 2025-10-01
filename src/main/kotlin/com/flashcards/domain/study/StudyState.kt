package com.flashcards.domain.study

import jakarta.persistence.*
import java.io.Serializable
import java.time.LocalDate
import java.util.UUID

/**
 * 학습 상태 복합키
 * - userId: 학습자 ID
 * - cardId: 학습 대상 카드 ID
 */
@Embeddable
data class StudyStateId(
    @Column(name = "user_id", columnDefinition = "BINARY(16)") var userId: UUID? = null,
    @Column(name = "card_id", columnDefinition = "BINARY(16)") var cardId: UUID? = null
) : Serializable

/**
 * 학습 상태 엔티티
 * 
 * 기능:
 * - 학습 진도 추적: 사용자별 카드별 학습 상태 관리
 * - 학습 스케줄링: nextDue 기반으로 오늘 학습할 카드 목록 조회
 * - SM-2 변형 알고리즘 적용:
 *   - intervalDays: 복습 간격 (일 단위)
 *   - ease: 난이도 계수 (기본 2.5)
 *   - reps: 연속 정답 횟수
 *   - lapses: 실패 횟수 (망각 횟수)
 *   - nextDue: 다음 복습 예정일
 * - 학습 통계 제공: 카드별 난이도 추이 분석에 활용
 * 
 * 인덱스:
 * - idx_state_user_due: user_id, next_due 복합 인덱스 (오늘 학습할 카드 조회 최적화)
 */
@Entity
@Table(
    name = "study_states",
    indexes = [Index(name = "idx_state_user_due", columnList = "user_id, next_due")]
)
class StudyState(
    @EmbeddedId var id: StudyStateId = StudyStateId(),
    
    @Column(name = "interval_days", nullable = false) 
    var intervalDays: Int = 1,   // SM-2: 복습 간격 (일)
    
    @Column(nullable = false) 
    var ease: Double = 2.5,   // SM-2: 난이도 계수
    
    @Column(nullable = false) 
    var reps: Int = 0,   // SM-2: 연속 정답 횟수
    
    @Column(nullable = false) 
    var lapses: Int = 0,   // SM-2: 실패 횟수 (망각 횟수)
    
    @Column(name = "next_due", nullable = false) 
    var nextDue: LocalDate   // 다음 복습 예정일 (학습 스케줄링 핵심)
)