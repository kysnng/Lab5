package org.example.ControlUnits

import org.example.Entity.*
import java.io.*
import java.util.*
import kotlin.collections.ArrayList

/**
 *  Класс CollectionManager, отвечающий за хранение и управление локальной коллекцией, а также ее сохранением в файл и выгрузкой из него.
 *  Объект класса CollectionManager может быть только один. Иначе говоря, для класса реализован Singleton.
 *  Singleton в классе реализован с помощью статистического поля instance с делегированием lazy, который создает элемент коллекции.
 */


class CollectionManager private constructor(private val collection: ArrayList<HumanBeing>) {
    companion object{
        val instance: CollectionManager by lazy { CollectionManager(ArrayList()) }
    }

    /**
     * Функция получения локальной коллекции ArrayList<HumanBeing>.
     * @return возвращает локальную коллекцию.
     */
    fun getAll(): ArrayList<HumanBeing>{
        return collection
    }

    /**
     * Функция перемешивания локальной коллекции ArrayList<HumanBeing>.
     */
    fun shuffle(){
        collection.shuffle()
    }

    /**
     * Функция добавления элемента в локальную коллекцию ArrayList<HumanBeing>.
     * @param element принимает объект HumanBeing для добавления в локальную коллекцию.
     */
    fun add(element: HumanBeing){
        collection.add(element)
    }

    /**
     * Функция сортировки локальной коллекции ArrayList<HumanBeing>.
     */
    fun sort(){
        collection.sortedWith(compareBy({it.id}))
    }

    /**
     * Функция получения размера локальной коллекции ArrayList<HumanBeing>.
     * @return возвращает результат функции size() класса ArrayList<E>.
     * @see java.util.ArrayList
     */
    fun length() : Int{
        return collection.size
    }

    /**
     * Функция удаления элемента по id из локальной коллекции ArrayList<HumanBeing>.
     * @param id принимает Int
     * @return возвращает результат функции removeIf().
     */
    fun removeById(id: Int) : Boolean{
        return collection.removeIf { it.id == id}
    }

    /**
     * Функция удаления всех элементов из локальной коллекции ArrayList<HumanBeing>.
     * @see java.util.ArrayList
     */
    fun clear(){
        collection.clear()
    }

    /**
     * Функция получения элемента локальной коллекции ArrayList<HumanBeing> по полю id.
     * @param id принимает Int.
     * @return возвращает результат функции find() класса ArrayList<E>.
     * @see java.util.ArrayList
     */
    fun getById(id: Int) : HumanBeing?{
        return collection.find { it.id == id}
    }

/*    fun updateById(id: Int, newElement: HumanBeing) : Boolean{
        val index = collection.indexOfFirst { it.id == id }
        return if(index !=-1){
            collection[index] = newElement
            true
        } else {
            false
        }
    } */

    /**
     * Функция сохранения локальной коллекции ArrayList<HumanBeing> в файл формата .csv.
     * @param fileName принимает файл формата .csv в виде пути к этому файлу (String).
     * Использует BufferWriter для более быстрой загрузки локальной коллекции в файл.
     * @see java.io.BufferedWriter
     */
    fun saveToFile(fileName: String) {
        try {
            BufferedWriter(FileWriter(fileName)).use { writer ->
                /** Запись заголовков .csv */
                writer.write("id,name,coordinates_x,coordinates_y,creationDate,realHero,hasToothpick,impactSpeed,soundtrackName,minutesOfWaiting,weaponType,car_name\n")

                /** Запись данных в файл .csv */
                collection.forEach { human ->
                    writer.write(
                        "${human.id},${human.name},${human.coordinates.x},${human.coordinates.y}," +
                                "${human.creationDate.time},${human.realHero},${human.hasToothpick},${human.impactSpeed}," +
                                "${human.soundtrackName},${human.minutesOfWaiting},${human.weaponType},${human.car?.name}\n"
                    )
                }
            }
        } catch (e: IOException) {
            println("Ошибка при сохранении файла: ${e.message}")
        }
    }

    /**
     * Функция выгрузки из файла формата .csv в локальную коллекцию ArrayList<HumanBeing>.
     * @param fileName принимает файл формата .csv в виде пути к этому файлу (String).
     * Использует BufferReader для более быстрой выгрузки из файла в локальную коллекцию.
     * @see java.io.BufferedReader
     */
    fun loadFromFile(fileName: String) {
        try {
            BufferedReader(FileReader(fileName)).use { reader ->
                /** Пропуск заголовков .csv */
                reader.readLine()

                /** Цикл чтения данных и добавления элемента в локальную коллекцию */
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    val parts = line!!.split(",")
                    if (parts.size == 12) {
                        val id = parts[0].toInt()
                        val name = parts[1]
                        val coordinates = Coordinates(parts[2].toInt(), parts[3].toLong())
                        val creationDate = Date(parts[4].toLong())
                        val realHero = parts[5].toBoolean()
                        val hasToothpick = parts[6].toBoolean()
                        val impactSpeed = parts[7].toFloat()
                        val soundtrackName = parts[8]
                        val minutesOfWaiting = parts[9].toFloat()
                        val weaponType = if (parts[10].isEmpty() || parts[10].equals("null", ignoreCase = true)) {
                            null
                        } else {
                            try {
                                WeaponType.valueOf(parts[10])
                            } catch (e: IllegalArgumentException) {
                                println("Ошибка: некорректное значение weaponType: ${parts[10]}")
                                null
                            }
                        }
                        val car = if (parts[11].isEmpty() || parts[11].equals("null", ignoreCase = true)) {
                            null
                        } else {
                            Car(parts[11])
                        }

                        /** Создаем объект с использованием классического конструктора (без автоматической генерации).*/
                        val human = HumanBeing.createWithId(
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

                        /** Добавление объекта в локальную коллекцию */
                        collection.add(human)
                    }
                }
            }
        } catch (e: IOException) {
            println("Ошибка при загрузке файла: ${e.message}")
        } catch (e: IllegalArgumentException) {
            println("Ошибка при парсинге данных: ${e.message}")
        }
    }

    /** Функция вывода информации о коллекции, используется для команды info.
     * @see org.example.Commands.InfoCommand
     * @return Строки со значениями: Тип коллекции, Кол-во элементов, Дата инициализации.*/
    fun getInfo(): String{
        return """
            Тип коллекции: ${collection::class.simpleName}
            Кол-во элементов: ${collection.size}
            Дата инициализации: ${Date()}
        """.trimIndent()
    }
}