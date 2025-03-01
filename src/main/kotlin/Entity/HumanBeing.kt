package org.example.Entity

import java.util.*

class HumanBeing private constructor(
    public val id: Int, //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private var name: String, //Поле не может быть null, Строка не может быть пустой
    private var coordinates: Coordinates, //Поле не может быть null
    private val creationDate: Date, //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private var realHero: Boolean,
    private var hasToothpick: Boolean,
    private var impactSpeed: Float, //Максимальное значение поля: 68
    private var soundtrackName: String, //Поле не может быть null
    private var minutesOfWaiting: Float,
    private var weaponType: WeaponType?,
    private var car: Car?)
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

    fun update(
        name: String,
        coordinates: Coordinates,
        realHero: Boolean,
        hasToothpick: Boolean,
        impactSpeed: Float,
        soundtrackName: String,
        minutesOfWaiting: Float,
        weaponType: WeaponType?,
        car: Car?
    ) {
        this.name = name
        this.coordinates = coordinates
        this.realHero = realHero
        this.hasToothpick = hasToothpick
        this.impactSpeed = impactSpeed
        this.soundtrackName = soundtrackName
        this.minutesOfWaiting = minutesOfWaiting
        this.weaponType = weaponType
        this.car = car
    }

    override fun toString(): String {
        return """
            Человек:
                id: $id
                Имя: $name
                $coordinates
                Дата создания: $creationDate
                Настоящий герой: $realHero
                Есть зубочистка: $hasToothpick
                Скорость атаки: $impactSpeed
                Название саундтрека: $soundtrackName
                Время ожидания: $minutesOfWaiting
                Тип оружия: $weaponType
                Машина: $car
        """.trimIndent()
    }

}