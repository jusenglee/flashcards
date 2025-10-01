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
 * 첨부파일 엔티티 (후순위 기능)
 *
 * 기능:
 * - 카드/덱 등 OwnerType + ownerId 조합으로 첨부파일 연결
 * - 이미지/음성/파일 URI, MIME, 크기 정보를 저장해 프론트 제공
 */
@Entity
@Table(
    name = "attachments",
    indexes = [Index(name = "idx_attach_owner", columnList = "owner_type, owner_id")]
)
class Attachment(
    @Id @Convert(converter = UuidBinaryConverter::class)
    @Column(columnDefinition = "BINARY(16)")
    var id: UUID = UuidV7.new(),

    @Enumerated(EnumType.STRING) @Column(name = "owner_type", nullable = false)
    var ownerType: OwnerType,

    @Column(name = "owner_id", columnDefinition = "BINARY(16)", nullable = false)
    var ownerId: UUID,

    @Column(nullable = false) var uri: String,
    @Column var mime: String? = null,
    @Column var size: Long? = null
) : BaseTimeEntity()
