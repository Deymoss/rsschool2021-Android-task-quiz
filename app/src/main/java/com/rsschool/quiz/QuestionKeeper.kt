package com.rsschool.quiz

class QuestionKeeper {
    companion object {
    val questions = listOf<String>("Чему равна сумма углов треугольника",
        "Лучший язык программирования",
        "Алгоритм нахождения контрольной суммы",
        "Последний уровень Android API",
        "Создатель С++")
    val answers = arrayOf(arrayOf("360","180","270","90","60"),
                          arrayOf("Kotlin","C", "Java", "C++","Scratch","C++"),
                            arrayOf("CCR", "FTS", "CRC","WTF","STFU", "CRC"),
                          arrayOf("28","29","30","31","32","30"),
                          arrayOf("Я", "Денис Ритчи", "Стив Жопс", "Бьёрн Страуструп","Билл Гейтс","Бьёрн Страуструп"))
        var answered = mutableListOf<Int>(-1,-1,-1,-1,-1)
        var rightAnswers = listOf<Int>(1,3,2,2,3)
        var radioIds = mutableListOf<Int>(-1,-1,-1,-1,-1)
    }

}