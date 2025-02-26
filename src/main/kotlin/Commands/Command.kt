package org.example.Commands

interface Command {
    fun getName(): String
    fun execute() : String
}