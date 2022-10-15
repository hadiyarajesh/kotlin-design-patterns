package com.hadiyarajesh.designpatterns.builder_factory.vehicle.parts

class Engine private constructor(
    val type: Type,
    val transmission: Transmission
) : Part {
    override val selfPrice: Int
        get() = when (type) {
            Type.Petrol -> 1_00_000
            Type.Diesel -> 1_50_000
            Type.Gas -> 75_000
            Type.Electric -> 2_00_000
        }

    override val totalPrice: Int
        get() = selfPrice + transmission.totalPrice

    enum class Type {
        Petrol,
        Diesel,
        Gas,
        Electric
    }

    class Builder {
        lateinit var type: Engine.Type
        lateinit var transmission: Transmission

        fun setEngineType(type: Engine.Type): Builder {
            this.type = type
            return this
        }

        fun setTransmission(transmission: Transmission): Builder {
            this.transmission = transmission
            return this
        }

        fun build(): Engine {
            return Engine(type = this.type, transmission = this.transmission)
        }
    }
}