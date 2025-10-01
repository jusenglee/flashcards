// com/flashcards/domain/tag/Tag.kt
package com.flashcards.domain.tag

import com.flashcards.domain.support.BaseTimeEntity
import com.flashcards.domain.support.UuidBinaryConverter
import com.flashcards.domain.support.UuidV7
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

/**
 * 태그 엔티티
 *
 * 기능:
 * - 태그 생성/삭제: 중복 방지를 위해 unique 인덱스 구성
 * - 카드 검색/필터: 카드와의 다대다(CardTag) 관계로 분류 기능 제공
 */
@Entity
@Table(name = "tags")
class Tag(
    @Id @Convert(converter = UuidBinaryConverter::class)
    @Column(columnDefinition = "BINARY(16)")
    var id: UUID = UuidV7.new(),

    @Column(nullable = false, unique = true)
    var name: String
) : BaseTimeEntity()
