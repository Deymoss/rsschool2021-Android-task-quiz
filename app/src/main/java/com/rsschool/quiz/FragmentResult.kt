package com.rsschool.quiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentQuizBinding
import com.rsschool.quiz.databinding.FragmentResultBinding

class FragmentResult: Fragment() {
    private var binding: FragmentResultBinding? = null
    private val _binding: FragmentResultBinding
        get() {
            return binding as FragmentResultBinding
        }
    private lateinit var fragmentHandller: FragmentHandller

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentHandller) {
            fragmentHandller = context
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultBinding.inflate(inflater)
        return _binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var counterAnswers = 0
        var i = 0

        while(i < QuestionKeeper.answered.size)
        {
            println("${QuestionKeeper.answered[i]}    ${QuestionKeeper.rightAnswers[i]}")
            if(QuestionKeeper.answered[i] == QuestionKeeper.rightAnswers[i])
                counterAnswers++
            i++
        }
        _binding.textView.text = "Your result is $counterAnswers of 5"
        _binding.btnClose.setOnClickListener {
            fragmentHandller.close()
        }
        _binding.btnBack.setOnClickListener {
            i = 0
            while(i < QuestionKeeper.answered.size)
            {
                QuestionKeeper.answered[i] = -1
                QuestionKeeper.radioIds[i] = -1
                i++
            }
            fragmentHandller.nextQuestion(0)
        }
        _binding.btnShare.setOnClickListener {
            val string = createStr()
            val intent = Intent(Intent.ACTION_SEND)
            with(intent) {
                type = "text/plain"
                putExtra(
                    Intent.EXTRA_TEXT, "Мой результат $counterAnswers из 5"
                            + "\n\n" + string
                )
                putExtra(Intent.EXTRA_TITLE, "Quiz result")
            }
            startActivity(Intent.createChooser(intent, "Share result"))
        }

    }
    private fun createStr(): String{
        var stringToSend: String = ""
        var i = 0
        while(i < QuestionKeeper.answers.size) {
            stringToSend += "${i+1}) ${QuestionKeeper.questions[i]} " +
                    "\n Your answer: ${QuestionKeeper.answers[i][QuestionKeeper.answered[i]]} \n\n"
            i++
        }
        return stringToSend
    }
    companion object {
        @JvmStatic
        fun newInstance() : FragmentResult
        {
            return FragmentResult()
        }
    }
}