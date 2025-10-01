package com.flashcards.domain.study

import com.flashcards.domain.model.StudyGrade
import com.flashcards.domain.support.*
import jakarta.persistence.*
import java.util.UUID

@Entity @Table(
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