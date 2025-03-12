package org.example.Entity
/** Класс отвечающий за поле car объектов класса HumanBeing.
 * @see org.example.Entity.HumanBeing*/
class Car (val name: String?) {
    /** Переопределение toString для корректного вывода.*/
    override fun toString(): String {
        return "$name"
    }
}