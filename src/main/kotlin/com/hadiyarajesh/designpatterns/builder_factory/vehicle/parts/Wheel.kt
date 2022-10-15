package com.hadiyarajesh.designpatterns.builder_factory.vehicle.parts

class Wheel private constructor(
    val type: Type
) : Part {
    override val selfPrice: Int
        get() = when (type) {
            Type.Steel -> 10_000
            Type.Alloy -> 12_000
            Type.CarbonFibre -> 15_000
        }

    override val totalPrice: Int = selfPrice

    enum class Type {
        Steel,
        Alloy,
        CarbonFibre
    }

    class Factory(
        private val type: Type
    ) {
        /**
         *  Don't allow creating a single wheel.
         *  Wheels should only exist in a list.
         */
        fun createWheels(numWheels: Int): List<Wheel> {
            return generateSequence { Wheel(type) }.take(numWheels).toList()
        }
    }
}