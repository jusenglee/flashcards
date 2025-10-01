package com.flashcards.domain.deck

import com.flashcards.domain.user.User
import com.flashcards.domain.model.DeckVisibility
import com.flashcards.domain.support.BaseTimeEntity
import com.flashcards.domain.support.UuidBinaryConverter
import com.flashcards.domain.support.UuidV7
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "decks")
class Deck (
    @Id @Convert(converter = UuidBinaryConverter::class)
    @Column(columnDefinition = "BINARY(16)")
    var id: UUID = UuidV7.new(),

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "owner_id", nullable = false)
    var owner: User,

    @Column(nullable = false) var title: String,
    @Enumerated(EnumType.STRING) @Column(nullable = false)
    var visibility: DeckVisibility = DeckVisibility.PRIVATE,
) : BaseTimeEntity()