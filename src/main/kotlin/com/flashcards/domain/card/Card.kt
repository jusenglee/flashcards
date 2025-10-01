package com.flashcards.domain.card

import com.flashcards.domain.deck.Deck
import com.flashcards.domain.support.BaseTimeEntity
import com.flashcards.domain.support.UuidBinaryConverter
import com.flashcards.domain.support.UuidV7
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "cards", indexes = [Index(name = "idx_cards_deck", columnList = "deck_id")])
class Card(
    @Id @Convert(converter = UuidBinaryConverter::class)
    @Column(columnDefinition = "BINARY(16)")
    var id: UUID = UuidV7.new(),

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "deck_id", nullable = false)
    var deck: Deck,

    @Column(columnDefinition = "text", nullable = false) var front: String,
    @Column(columnDefinition = "text", nullable = false) var back: String,
    @Column(name = "difficulty_est") var difficultyEst: Double? = null
) : BaseTimeEntity()