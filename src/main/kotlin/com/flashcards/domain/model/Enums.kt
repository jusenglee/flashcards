package com.flashcards.domain.model

enum class DeckVisibility { PRIVATE, LINK, PUBLIC }
enum class DeckRole { VIEWER, EDITOR, OWNER }
enum class OwnerType { DECK, CARD }
enum class StudyGrade(val score: Int) { AGAIN(0), HARD(1), GOOD(2), EASY(3) }