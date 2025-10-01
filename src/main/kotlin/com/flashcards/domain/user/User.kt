package com.flashcards.domain.user

import com.flashcards.domain.support.BaseTimeEntity
import com.flashcards.domain.support.UuidBinaryConverter
import com.flashcards.domain.support.UuidV7
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "users")
class User(
    @Id @Convert(converter = UuidBinaryConverter::class)
    @Column(columnDefinition = "BINARY(16)")
    var id: UUID = UuidV7.new(),

    @Column(nullable = false, unique = true)
    var email: String,

    @Column(nullable = false)
    var passwordHash: String,   // BCrypt/Argon2 결과 저장

    @Column(nullable = true)
    var nickname: String? = null,

    @Column(nullable = false)
    var status: String = "active",   // active, suspended, deleted

    @Column(nullable = false)
    var emailVerified: Boolean = false,

    @Column
    var lastLoginAt: Instant? = null,

    @Column(nullable = false)
    var failedAttempts: Int = 0,

    @Column
    var lockedUntil: Instant? = null,

    // 선택적 추가 정보
    @Column var profileImageUrl: String? = null,
    @Column var bio: String? = null,
    @Column var language: String? = "ko",
    @Column var timezone: String? = "Asia/Seoul"
) : BaseTimeEntity()