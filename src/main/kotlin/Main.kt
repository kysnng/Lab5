package org.example
import org.example.*
import org.example.Entity.Invoker

fun main(args: Array<String>) {
    val invoker = Invoker()
    while(true){
        print("$ ")
        val userSentence = readln()
        println(invoker.invoke(userSentence))
    }
}