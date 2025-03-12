package org.example.Commands

import org.example.ControlUnits.CollectionManager
import org.example.ControlUnits.InputManager
import org.example.Entity.Car
import org.example.Entity.Coordinates
import org.example.Entity.HumanBeing
import org.example.Entity.WeaponType

/**
 * Команда Add реализующая выполнение команды add по запросу пользователя в интерактивном режиме.
 *
 * Отвечает за добавление нового объекта в коллекцию, поля которого задаются пользователем в интерактивном режиме.
 *
 * @param collectionManager принимает в параметры CollectionManager для добавления в коллекцию.
 * @param im принимает InputManager в параметры для удобного и корректного внесения данных пользователем в интерактивном режиме.
 * @see org.example.ControlUnits.CollectionManager - класс отвечающий за управление локальной коллекцией, ее загрузкой в файл и выгрузкой из него.
 * @see org.example.ControlUnits.InputManager - класс отвечающий за парсинг введенных пользователем данных для локальной коллекции объектов HumanBeing.
 * @see org.example.Entity.HumanBeing - основополагающий класс, объекты которого в локальной коллекции.
 */

class AddCommand(private val collectionManager: CollectionManager, private val im: InputManager) : Command {
    override fun execute(arguments: String?) {
        println("Добавление нового элемента в коллекцию.")
        val name = im.readString("Введите имя: "){
            it.isNotEmpty()
        }
        val x = im.readInt("Введите координату x (максимум 749): "){
            it <= 749 && it >= -749
        }
        val y = im.readLong("Введите координату y (максимум 749): "){
            it <= 749 && it >= -749
        }
        val coordinates = Coordinates(x, y)
        val realHero = im.readBoolean("Это реальный герой? (true/false): ")
        val hasToothpick = im.readBoolean("Имеет зубочистку? (true/false): ")
        val impactSpeed = im.readFloat("Введите скорость удара: (максимум 68) "){
            it in 0f..68f
        }
        val soundtrackName = im.readString("Введите название саундтрека: "){
            it.isNotEmpty()
        }
        val minutesOfWaiting = im.readFloat("Введите минуты ожидания: ")
        val weaponType = im.readEnum("Введите тип оружия (AXE, RIFLE, KNIFE, MACHINE_GUN): ", WeaponType.values())
        val carName = im.readString("Введите наименование автомобиля (если он есть, в ином случае оставьте пустым): ")
        val car = if(carName.isNotEmpty())  Car (carName) else null

        val newHuman = HumanBeing(
            name = name,
            coordinates = coordinates,
            realHero = realHero,
            hasToothpick = hasToothpick,
            impactSpeed = impactSpeed,
            soundtrackName = soundtrackName,
            minutesOfWaiting = minutesOfWaiting,
            weaponType = weaponType,
            car = car
        )

        collectionManager.add(newHuman)
        println("Элемент успешно добавлен в коллекцию")
    }
}