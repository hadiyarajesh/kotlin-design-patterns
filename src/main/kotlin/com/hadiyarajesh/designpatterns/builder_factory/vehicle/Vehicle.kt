package com.hadiyarajesh.designpatterns.builder_factory.vehicle

import com.hadiyarajesh.designpatterns.builder_factory.vehicle.parts.Chasis
import com.hadiyarajesh.designpatterns.builder_factory.vehicle.parts.Engine
import com.hadiyarajesh.designpatterns.builder_factory.vehicle.parts.WheelBase

class Vehicle private constructor(
    val wheelBase: WheelBase,
    val chasis: Chasis,
    val engine: Engine
) {
    val price: Int = wheelBase.totalPrice + chasis.totalPrice + engine.totalPrice

    class Builder {
        private lateinit var wheelBase: WheelBase
        private lateinit var chasis: Chasis
        private lateinit var engine: Engine

        fun setWheelBase(wheelBase: WheelBase): Builder {
            this.wheelBase = wheelBase
            return this
        }

        fun setChasis(chasis: Chasis): Builder {
            this.chasis = chasis
            return this
        }

        fun setEngine(engine: Engine): Builder {
            this.engine = engine
            return this
        }

        fun build(): Vehicle {
            return Vehicle(
                wheelBase = wheelBase,
                chasis = chasis,
                engine = engine
            )
        }

    }
}