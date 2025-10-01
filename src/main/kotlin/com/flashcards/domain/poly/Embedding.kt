package com.flashcards.domain.poly

import com.flashcards.domain.model.OwnerType
import com.flashcards.domain.support.*
import jakarta.persistence.*
import java.util.UUID

@Entity @Table(
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