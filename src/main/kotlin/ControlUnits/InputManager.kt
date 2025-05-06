package org.example.ControlUnits

/** Класс InputManager, отвечающий за парсинг введенных пользователем данных. Необходим для реализации команд add и update,
 * где от пользователя требуется ввести некие данные.
 * @see org.example.Commands.AddCommand
 * @see org.example.Commands.UpdateCommand
 * */

class InputManager {
    // Валидатор используется для определения существования в значении. В Kotlin нельзя просто в if внести какое-то значение
    /**
     * Функция, используемая для чтения строковых данных.
     * @param prompt принимает строку, которая запрашивает пользователя ввести какие-то данные.
     * @param validator используется для задания ограничений для вводимого значения.
     * @return возвращает введенное пользователем число, если оно прошло проверку на корректность и условие в validator.
     */
    fun readString (prompt: String, validator: (String) -> Boolean = {true}) : String {
        while (true){
            print(prompt)
            val input = readLine()?.trim() ?: ""
            if(validator(input)){
                return input
            } else {
                println("Некорректный ввод. Пожалуйста, попробуйте снова.")
            }
        }
    }

    /**
     * Функция, используемая для чтения целочисленных данных.
     * @param prompt принимает строку, которая запрашивает пользователя ввести какие-то данные.
     * @param validator используется для задания ограничений для вводимого значения.
     * @return возвращает введенное пользователем число, если оно прошло проверку на корректность и условие в validator.
     */
    fun readInt(prompt: String, validator: (Int) -> Boolean = {true}) : Int {
        while(true){
            print(prompt)
            val input = readLine()?.trim() ?: ""
            try {
                val value = input.toInt()
                if(validator(value)){
                    return value
                }else{
                    println("Некорректный ввод. Пожалуйста, попробуйте снова.")
                }
            } catch (e: NumberFormatException) {
                println("Ошибка: Введите целое число.")
            }
        }
    }

    /**
     * Функция, используемая для чтения больших целочисленных данных.
     * @param prompt принимает строку, которая запрашивает пользователя ввести какие-то данные.
     * @param validator используется для задания ограничений для вводимого значения.
     * @return возвращает введенное пользователем число, если оно прошло проверку на корректность и условие в validator.
     */
    fun readLong(prompt: String, validator: (Long) -> Boolean = {true}) : Long {
        while(true){
            print(prompt)
            val input = readLine()?.trim() ?: ""
            try{
                val value = input.toLong()
                if(validator(value)){
                    return value
                } else{
                    println("Некорректный ввод. Пожалуйста, попробуйте снова.")
                }
            } catch (e: NumberFormatException) {
                println("Ошибка: Введите целое число.")
            }
        }
    }

    /**
     * Функция, используемая для чтения чисел с плавающей точкой.
     * @param prompt принимает строку, которая запрашивает пользователя ввести какие-то данные.
     * @param validator используется для задания ограничений для вводимого значения.
     * @return возвращает введенное пользователем число, если оно прошло проверку на корректность и условие в validator.
     */
    fun readFloat(prompt: String, validator: (Float) -> Boolean = {true}) : Float {
        while(true){
            print(prompt)
            val input = readLine()?.trim() ?: ""
            try {
                val value = input.toFloat()
                if(validator(value)){
                    return value
                }else{
                    println("Некорректный ввод. Пожалуйста, попробуйте снова.")
                }
            } catch (e: NumberFormatException) {
                println("Ошибка: Введите целое число.")
            }
        }
    }

    /**
     * Функция, используемая для чтения логических данных.
     * @param prompt принимает строку, которая запрашивает пользователя ввести какие-то данные.
     * @return возвращает введенное пользователем логическое значение, если оно прошло проверку на корректность.
     */
    fun readBoolean(prompt: String) : Boolean {
        while(true){
            print(prompt)
            val input = readLine()?.trim()?.lowercase() ?: ""
            if (input == "true") return true else if (input=="false") return false else OutputManager.output("Ошибка: Введите 'true' или 'false'")
//            when (input){
//                "true" -> return true
//                "false" -> return false
//                else -> println("Ошибка: Введите 'true' или 'false'.")
//            }
        }
    }

    /**
     * Функция, используемая для чтения элементов списка.
     * @param prompt принимает строку, которая запрашивает пользователя ввести какие-то данные.
     * @param enumValues принимает коллекцию Array<T>, где T - некий Enum.
     * @return возвращает введенное пользователем число, если оно прошло проверку на корректность.
     */
    fun <T: Enum<T>> readEnum (prompt: String, enumValues: Array<T>) : T? {
        while(true) {
            print(prompt)
            val input = readLine()?.trim()?.uppercase() ?: ""
            if(input.isEmpty()){
                return null
            }
            try {
                return enumValues.first() { it.name == input }
            } catch (e: NoSuchElementException) {
                println("Ошибка: Введите одно из предложенных значений: ${enumValues.joinToString(", ") {it.name}}.")
            }
        }
    }
}