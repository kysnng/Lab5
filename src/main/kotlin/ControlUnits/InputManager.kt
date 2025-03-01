package org.example.ControlUnits

import org.example.Entity.WeaponType

class InputManager {
    // Валидатор используется для определения существования в значении. В Kotlin нельзя просто в if внести какое-то значение
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

    fun readBoolean(prompt: String) : Boolean {
        while(true){
            print(prompt)
            val input = readLine()?.trim()?.lowercase() ?: ""
            when (input){
                "true" -> return true
                "false" -> return false
                else -> println("Ошибка: Введите 'true' или 'false'.")
            }
        }
    }

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