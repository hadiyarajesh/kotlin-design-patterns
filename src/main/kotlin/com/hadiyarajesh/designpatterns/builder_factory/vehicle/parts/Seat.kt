package com.hadiyarajesh.designpatterns.builder_factory.vehicle.parts

class Seat private constructor(
    val type: Type
) : Part {
    override val selfPrice: Int
        get() = when (type) {
            Type.Cloth -> 10_000
            Type.Rexine -> 12_000
            Type.Leather -> 15_000
        }

    override val totalPrice: Int = selfPrice

    enum class Type {
        Cloth,
        Rexine,
        Leather
    }

    class Factory(
        private val type: Type
    ) {
        /**
         *  Don't allow creating a single seat.
         *  Seats should only exist in a list.
         */
        fun createSeats(numSeats: Int): List<Seat> {
            return generateSequence { Seat(type) }.take(numSeats).toList()
        }
    }
}