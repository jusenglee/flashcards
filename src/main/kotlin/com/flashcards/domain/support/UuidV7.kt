package com.flashcards.domain.support

import com.github.f4b6a3.uuid.UuidCreator
import java.util.UUID

object UuidV7 {

    fun new(): UUID = UuidCreator.getTimeOrderedEpoch();
}