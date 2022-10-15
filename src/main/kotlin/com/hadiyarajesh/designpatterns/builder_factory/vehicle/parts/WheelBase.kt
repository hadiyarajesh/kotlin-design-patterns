package com.hadiyarajesh.designpatterns.builder_factory.vehicle.parts

class WheelBase private constructor(
    val size: Size,
    val wheels: List<Wheel>
) : Part {
    override val selfPrice: Int
        get() = when (size) {
            Size.Small -> 1_00_000
            Size.Medium -> 1_25_000
            Size.Large -> 1_50_000
        }

    override val totalPrice: Int
        get() = selfPrice + wheels.sumOf { it.totalPrice }

    enum class Size {
        Small,
        Medium,
        Large
    }

    class Builder {
        private lateinit var size: WheelBase.Size
        private lateinit var chasis: Chasis
        private lateinit var wheelFactory: Wheel.Factory
        private var spareWheel: Boolean = false

        fun setSize(size: WheelBase.Size): Builder {
            this.size = size
            return this
        }

        fun setWheelFactory(wheelFactory: Wheel.Factory): Builder {
            this.wheelFactory = wheelFactory
            return this
        }

        fun setSpareWheel(spareWheel: Boolean): Builder {
            this.spareWheel = spareWheel
            return this
        }

        fun build(): WheelBase {
            return WheelBase(
                size = this.size,
                wheels = this.wheelFactory.createWheels(numWheels = 4 + if (spareWheel) 1 else 0)
            )
        }
    }
}