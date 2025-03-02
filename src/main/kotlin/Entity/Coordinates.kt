package org.example.Entity

class Coordinates (private val x: Int, private val y: Long) {
    init {
        require(x <= 749 && x >= -749) { "Координата x должна быть не больше 749." }
        require(y <= 749 && y >= -749){"Координата y должна быть не больше 749." }
    }
    override fun toString(): String {
        return "Координаты: (x=$x, y=$y)"
    }
}