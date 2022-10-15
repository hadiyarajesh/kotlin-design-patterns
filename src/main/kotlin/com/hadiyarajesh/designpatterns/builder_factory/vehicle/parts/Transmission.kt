package com.hadiyarajesh.designpatterns.builder_factory.vehicle.parts

class Transmission(
    val type: Type
) : Part {
    override val selfPrice: Int
        get() = when (type) {
            Type.Auto -> 1_00_000
            Type.Manual -> 80_000
        }

    override val totalPrice: Int = selfPrice

    enum class Type {
        Auto,
        Manual
    }
}