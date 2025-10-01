package com.flashcards.domain.poly


import com.flashcards.domain.model.OwnerType
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
import java.util.UUID

/**
 * 신고 엔티티 (후순위: 운영자 기능)
 *
 * 기능:
 * - OwnerType/ownerId 로 신고 대상(카드/덱 등) 지정
 * - 신고 사유/상태 관리: 운영자의 처리 흐름(open/resolved 등)을 지원
 * - reporterId 로 신고자 추적
 */
@Entity
@Table(name = "reports")
class Report(
    @Id @Convert(converter = UuidBinaryConverter::class)
    @Column(columnDefinition = "BINARY(16)")
    var id: UUID = UuidV7.new(),

    @Enumerated(EnumType.STRING) @Column(name = "owner_type", nullable = false)
    var ownerType: OwnerType,

    @Column(name = "owner_id", columnDefinition = "BINARY(16)", nullable = false)
    var ownerId: UUID,

    @Column(nullable = false) var reason: String,
    @Column(nullable = false) var status: String = "open",

    @Column(name = "reporter_id", columnDefinition = "BINARY(16)") var reporterId: UUID? = null
) : BaseTimeEntity()
