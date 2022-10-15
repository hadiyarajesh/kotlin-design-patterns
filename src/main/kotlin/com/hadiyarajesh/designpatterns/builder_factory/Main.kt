package com.hadiyarajesh.designpatterns.builder_factory

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.UsageError
import com.hadiyarajesh.designpatterns.builder_factory.vehicle.Vehicle
import com.hadiyarajesh.designpatterns.builder_factory.vehicle.parts.*

fun main(args: Array<String>) = VehicleBuilder().main(args)

class VehicleBuilder : CliktCommand() {
    override fun run() {
        println(
            """
        |Welcome to vehicle builder
        |Select your own wheelbase, chasis and engine to build your vehicle.   
        """.trimMargin()
        )
        println()

        //region ----- Build wheelbase -----
        val wheelBaseSizeInput: String? =
            prompt(text = "Select Wheelbase size: (S)mall, (M)edium, (L)arge ") {
                if (it.lowercase() in ("s, m, l")) it.lowercase() else throw UsageError("$it is not a valid wheelbase size")
            }

        val wheelTypeInput: String? =
            prompt("Select Wheel type: (S)teel, (A)lloy, (C)arbonFibre") {
                if (it.lowercase() in ("s, a, c")) it.lowercase() else throw UsageError("$it is not a valid wheel type")
            }

        val provideSpareWheel: Boolean = confirm(text = "Do you want a spare wheel?", default = false)!!

        val wheelBaseBuilder = WheelBase.Builder()

        when (wheelBaseSizeInput) {
            "s" -> wheelBaseBuilder.setSize(WheelBase.Size.Small)
            "m" -> wheelBaseBuilder.setSize(WheelBase.Size.Medium)
            "l" -> wheelBaseBuilder.setSize(WheelBase.Size.Large)
            else -> throw UsageError("Invalid wheelbase size")
        }
        val wheelType = when (wheelTypeInput) {
            "s" -> Wheel.Type.Steel
            "a" -> Wheel.Type.Alloy
            "c" -> Wheel.Type.CarbonFibre
            else -> throw UsageError("Invalid wheel type")
        }
        wheelBaseBuilder.setWheelFactory(Wheel.Factory(wheelType))
        wheelBaseBuilder.setSpareWheel(provideSpareWheel)
        //endregion
        println()

        //region ----- Build chasis -----
        val chasisTypeInput: String? =
            prompt("Select Chasis type: (H)atchback, (S)edan, SU(V) ") {
                if (it.lowercase() in ("h, s, v")) it.lowercase() else throw UsageError("$it is not a valid chasis type")
            }

        val seatTypeInput: String? =
            prompt("Select Seat type: (C)loth, (R)exine, (L)eather") {
                if (it.lowercase() in ("c, r, l")) it.lowercase() else throw UsageError("$it is not a valid seat type")
            }

        val chasisBuilder = Chasis.Builder()

        when (chasisTypeInput) {
            "h" -> chasisBuilder.setChasisType(Chasis.Type.HatchBack)
            "s" -> chasisBuilder.setChasisType(Chasis.Type.Sedan)
            "v" -> chasisBuilder.setChasisType(Chasis.Type.SUV)
            else -> throw UsageError("Invalid chasis type")
        }

        val seatTpe = when (seatTypeInput) {
            "c" -> Seat.Type.Cloth
            "r" -> Seat.Type.Rexine
            "l" -> Seat.Type.Leather
            else -> throw UsageError("Invalid seat type")
        }

        chasisBuilder.setSeatFactory(Seat.Factory(seatTpe))
        //endregion
        println()

        //region ----- Build engine -----
        val engineTypeInput: String? =
            prompt("Select Engine type: (p)etrol, (D)iesel, (G)as, (E)lectric) ") {
                if (it.lowercase() in ("p, d, g, e")) it.lowercase() else throw UsageError("$it is not a valid engine type")
            }

        val engineBuilder = Engine.Builder()

        when (engineTypeInput) {
            "p" -> engineBuilder.setEngineType(Engine.Type.Petrol)
            "d" -> engineBuilder.setEngineType(Engine.Type.Diesel)
            "g" -> engineBuilder.setEngineType(Engine.Type.Gas)
            "e" -> engineBuilder.setEngineType(Engine.Type.Electric)
            else -> throw UsageError("Invalid engine type")
        }

        val provideAutoTransmission: Boolean =
            confirm(text = "Do you want an automatic transmission?", default = false)!!

        val transmissionType = if (provideAutoTransmission) Transmission.Type.Auto else Transmission.Type.Manual
        engineBuilder.setTransmission(Transmission(transmissionType))
        //endregion
        println()

        val vehicle = Vehicle.Builder()
            .setWheelBase(wheelBaseBuilder.build())
            .setChasis(chasisBuilder.build())
            .setEngine(engineBuilder.build())
            .build()

        println("We've build your vehicle")
        println("Total Vehicle cost is: ${vehicle.price}")
    }
}