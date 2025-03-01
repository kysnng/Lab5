package org.example.Commands

interface Command {
    fun execute(arguments: String? = null)
}