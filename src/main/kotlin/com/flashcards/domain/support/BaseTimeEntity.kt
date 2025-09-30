package com.flashcards.domain.support

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import java.time.Instant


@MappedSuperclass
abstract class BaseTimeEntity {
    @Column(nullable = false) var createdAt: Instant = Instant.now()
    @Column(nullable = false) var updatedAt: Instant = Instant.now()

    @PrePersist fun onCreate() { createdAt = Instant.now(); updatedAt = createdAt}
    @PreUpdate fun onUpdate() { updatedAt = Instant.now() }

}