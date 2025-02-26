package org.example.Entity

import java.util.*

class HumanBeing private constructor(
    private val id: Int, //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private val name: String, //Поле не может быть null, Строка не может быть пустой
    private val coordinates: Coordinates, //Поле не может быть null
    private val creationDate: Date, //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private val realHero: Boolean,
    private val hasToothpick: Boolean,
    private val impactSpeed: Float, //Максимальное значение поля: 68
    private val soundtrackName: String, //Поле не может быть null
    private val minutesOfWaiting: Float,
    private val weaponType: WeaponType?,
    private val car: Car?)
{
    constructor( // Задаем второй конструктор для корректного и безопасного введения автоматически заполняемых данных (id, дата)
        name: String,
        coordinates: Coordinates,
        realHero: Boolean,
        hasToothpick: Boolean,
        impactSpeed: Float,
        soundtrackName: String,
        minutesOfWaiting: Float,
        weaponType: WeaponType?,
        car: Car?
    ) : this( // Вызов основного конструктора
        generateId(), // Генерация ID
        name,
        coordinates,
        Date(), // Автоматическая генерация даты
        realHero,
        hasToothpick,
        impactSpeed,
        soundtrackName,
        minutesOfWaiting,
        weaponType,
        car
    )

    init {
        require(id > 0) { "Id должен быть больше 0" }
        require(name.isNotEmpty()) { "Имя не должно быть пустым" }
        require(impactSpeed <= 68) { "impactSpeed должно быть меньше 68" }
    }

    companion object{
        private var lastId = 0
        private fun generateId(): Int{
            return ++lastId
        }
    }

}