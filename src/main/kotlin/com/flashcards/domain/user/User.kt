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

/**
 * 회원 엔티티
 * 
 * 기능:
 * - 회원가입/로그인/로그아웃
 * - 이메일 인증 (emailVerified 필드)
 * - 프로필 수정 (nickname, profileImageUrl, language, timezone)
 * - 보안 기능 (비밀번호 변경, 계정 잠금, 실패 횟수 관리)
 *   - failedAttempts: 로그인 실패 횟수 추적
 *   - lockedUntil: 계정 잠금 해제 시간
 * - 사용자 상태 관리 (status: active, suspended, deleted)
 */
@Entity
@Table(name = "users")
class User(
    @Id @Convert(converter = UuidBinaryConverter::class)
    @Column(columnDefinition = "BINARY(16)")
    var id: UUID = UuidV7.new(),

    @Column(nullable = false, unique = true)
    var email: String,

    @Column(nullable = false)
    var passwordHash: String,   // BCrypt/Argon2 결과 저장 - 비밀번호 변경 기능에 사용

    @Column(nullable = true)
    var nickname: String? = null,   // 프로필 수정 기능

    @Column(nullable = false)
    var status: String = "active",   // active, suspended, deleted - 사용자 상태 관리

    @Column(nullable = false)
    var emailVerified: Boolean = false,   // 이메일 인증 상태

    @Column
    var lastLoginAt: Instant? = null,   // 로그인 추적

    @Column(nullable = false)
    var failedAttempts: Int = 0,   // 보안: 로그인 실패 횟수 관리

    @Column
    var lockedUntil: Instant? = null,   // 보안: 계정 잠금 기능

    // 프로필 수정 기능에 사용되는 선택적 정보
    @Column var profileImageUrl: String? = null,   // 프로필 이미지 URL
    @Column var bio: String? = null,   // 사용자 소개
    @Column var language: String? = "ko",   // 언어 설정
    @Column var timezone: String? = "Asia/Seoul"   // 타임존 설정 (학습 통계에 활용)
) : BaseTimeEntity()
