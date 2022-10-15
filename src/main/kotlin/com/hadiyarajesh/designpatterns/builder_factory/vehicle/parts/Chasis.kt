package com.hadiyarajesh.designpatterns.builder_factory.vehicle.parts

class Chasis private constructor(
    private val type: Type,
    private val seats: List<Seat>
) : Part {
    override val selfPrice: Int
        get() = when (type) {
            Type.Sedan -> 1_00_000
            Type.HatchBack -> 1_50_000
            Type.SUV -> 2_00_000
        }

    override val totalPrice: Int
        get() = selfPrice + seats.sumOf { it.totalPrice }

    enum class Type {
        Sedan,
        HatchBack,
        SUV
    }

    class Builder {
        lateinit var type: Chasis.Type
        lateinit var seatFactory: Seat.Factory

        fun setChasisType(type: Chasis.Type): Builder {
            this.type = type
            return this
        }

        fun setSeatFactory(seatFactory: Seat.Factory): Builder {
            this.seatFactory = seatFactory
            return this
        }

        fun build(): Chasis {
            val numSeats = when (type) {
                Type.Sedan -> 5
                Type.HatchBack -> 3
                Type.SUV -> 7
            }

            return Chasis(
                type = this.type,
                seats = this.seatFactory.createSeats(numSeats)
            )
        }
    }
}