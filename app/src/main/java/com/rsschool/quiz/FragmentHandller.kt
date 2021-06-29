package com.rsschool.quiz

interface FragmentHandller {
    fun openEndFragment()

    fun nextQuestion(questionIndex: Int)
    fun prevQuestion(questionIndex: Int)

    fun close()
}