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
import jakarta.persistence.Index
import jakarta.persistence.Table
import java.util.UUID

/**
 * 임베딩 엔티티 (후순위: 의미 기반 검색)
 *
 * 기능:
 * - OwnerType/ownerId 기준으로 카드/덱 등 컨텐츠 임베딩 메타데이터 저장
 * - 모델명, 차원 정보 기록 → 외부 벡터 스토리지와 연동 시 참조
 */
@Entity
@Table(
    name = "embeddings",
    indexes = [Index(name = "idx_embed_owner", columnList = "owner_type, owner_id")]
)
class Embedding(
    @Id @Convert(converter = UuidBinaryConverter::class)
    @Column(columnDefinition = "BINARY(16)")
    var id: UUID = UuidV7.new(),

    @Enumerated(EnumType.STRING) @Column(name = "owner_type", nullable = false)
    var ownerType: OwnerType,

    @Column(name = "owner_id", columnDefinition = "BINARY(16)", nullable = false)
    var ownerId: UUID,

    @Column(nullable = false) var model: String,
    @Column(nullable = false) var dim: Int,
    // 벡터 DB 미도입 상태 → raw 저장 안 함. 필요 시 별도 테이블/스토리지
) : BaseTimeEntity()
