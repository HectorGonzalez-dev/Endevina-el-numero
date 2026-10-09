package com.example.adivinaelnumero

// Clase diseñada para instanciar las entradas del ranking de forma eficiente
data class RankingEntry(val initials: String, val attempts: Int)

// Un object es una instancia unica compartida entre las actividades
object RankingStore {

    // Una lista modificable de las entradas del ranking
    val entries = mutableListOf<RankingEntry>()

    fun add(initials: String, attempts: Int) {
        entries.add(RankingEntry(initials, attempts))
        entries.sortBy { it.attempts } // Al agregar una nueva entrada, reordena el ranking
    }
}
