package org.example.Entity

import java.util.*

/** Класс HumanBeing объекты которого хранятся в локальной коллекции ArrayList<HumanBeing>,
 * реализованной через класс CollectionManager.
 * @param id - уникальный идентификатор объекта. Положителен, не может повторяться, генерируется автоматически.
 * @param name - имя персонажа. Не может быть пустым.
 * @param coordinates - координаты местонахождения персонажа. Не может быть пустым.
 * @param creationDate - дата создания объекта. Генерируется автоматически.
 * @param realHero - является ли персонаж героем (да - true, нет - false). Не может быть пустым.
 * @param hasToothpick - держит ли зубочистку в зубах (да - true, нет - false). Не может быть пустым.
 * @param impactSpeed - скорость удара. Не может быть пустым.
 * @param soundtrackName - название "главной темы" персонажа. Не может быть пустым.
 * @param minutesOfWaiting - время, необходимое для прибытия персонажа. Не может быть пустым.
 * @param weaponType - оружие персонажа. Может отсутствовать (быть пустым).
 * @param car - транспорт персонажа. Может отсутствовать (быть пустым).
 * @see org.example.ControlUnits.CollectionManager*/

data class HumanBeing private constructor(
    public val id: Int, //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    public var name: String, //Поле не может быть null, Строка не может быть пустой
    public var coordinates: Coordinates, //Поле не может быть null
    public val creationDate: Date, //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    public var realHero: Boolean,
    public var hasToothpick: Boolean,
    public var impactSpeed: Float, //Максимальное значение поля: 68
    public var soundtrackName: String, //Поле не может быть null
    public var minutesOfWaiting: Float,
    public var weaponType: WeaponType?,
    public var car: Car?)
{

    /**
     * Второй конструктор для корректного и безопасного введения автоматически заполняемых полей (id, дата).
     */
    constructor(
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
        generateId(), // Автоматическая генерация ID
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


    /** Проверка условий для данных id, name, impactSpeed.*/
    init {
        require(id > 0) { "Id должен быть больше 0" }
        require(name.isNotEmpty()) { "Имя не должно быть пустым" }
        require(impactSpeed <= 68) { "impactSpeed должно быть меньше 68" }
    }

    companion object{
        private var lastId = 0
        /** Статическая функция генерации уникального id
         * @return возвращает инкрементированную переменную lastId.*/
        private fun generateId(): Int = ++lastId

        /** Функция для создания объекта без генерации id и даты.
         * Необходим для корректного создания объектов при их загрузке из файла .csv.
         * @see org.example.ControlUnits.CollectionManager.loadFromFile - функция загрузки данных из коллекции.*/
        fun createWithId(
            id: Int,
            name: String,
            coordinates: Coordinates,
            creationDate: Date,
            realHero: Boolean,
            hasToothpick: Boolean,
            impactSpeed: Float,
            soundtrackName: String,
            minutesOfWaiting: Float,
            weaponType: WeaponType?,
            car: Car?
        ): HumanBeing {
            // Обновляем lastId, если переданный id больше текущего lastId
            if (id > lastId) {
                lastId = id
            }
            return HumanBeing(
                id,
                name,
                coordinates,
                creationDate,
                realHero,
                hasToothpick,
                impactSpeed,
                soundtrackName,
                minutesOfWaiting,
                weaponType,
                car
            )
        }
    }
/** Функция обновления данных объекта.
 * Используется для команды update.
 * @see org.example.Commands.UpdateCommand - команда обновления объекта.*/
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

    /** Переопределенная функция toString для корректного вывода данных объекта.
     * Используется для команды show.
     * @see org.example.Commands.ShowCommand - команда вывода всего содержимого локальной коллекции.*/
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