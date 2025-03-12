package org.example.Entity

/** Класс отвечающий за поле coordinates объектов класс HumanBeing.
 * @see org.example.Entity.HumanBeing*/
class Coordinates (val x: Int, val y: Long) {
    /** Проверка допустимых значений координат.*/
    init {
        require(x <= 749 && x >= -749) { "Координата x должна быть не больше 749." }
        require(y <= 749 && y >= -749){"Координата y должна быть не больше 749." }
    }

    /** Переопределение toString для корректного вывода.*/
    override fun toString(): String {
        return "Координаты: (x=$x, y=$y)"
    }
}