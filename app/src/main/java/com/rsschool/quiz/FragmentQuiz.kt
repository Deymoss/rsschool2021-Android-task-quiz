package com.rsschool.quiz

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentQuizBinding
import kotlin.properties.Delegates

class FragmentQuiz(private val value: Int): Fragment() {
    private var binding: FragmentQuizBinding? = null
    private val _binding: FragmentQuizBinding
        get() {
            return binding as FragmentQuizBinding
        }
    private lateinit var fragmentHandller: FragmentHandller
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuizBinding.inflate(inflater)
        return _binding.root
    }

    private fun changeQuestion() {

        binding?.apply {
            question.text = QuestionKeeper.questions[value]
            optionOne.text = QuestionKeeper.answers[value].first()
            optionTwo.text = QuestionKeeper.answers[value][1]
            optionThree.text = QuestionKeeper.answers[value][2]
            optionFour.text = QuestionKeeper.answers[value][3]
            optionFive.text = QuestionKeeper.answers[value][4]
        }
        if(QuestionKeeper.radioIds[value] != -1)
        {
            _binding.radioGroup.check(QuestionKeeper.radioIds[value])
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.nextButton.isEnabled = false

        _binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)

        _binding.nextButton.setOnClickListener {
            fragmentHandller.nextQuestion(value+1)
        }

        _binding.previousButton.setOnClickListener {
            fragmentHandller.prevQuestion(value-1)
        }

        _binding.radioGroup.setOnCheckedChangeListener { _, _ ->
            val radioId = _binding.radioGroup.checkedRadioButtonId
            QuestionKeeper.radioIds[value] = radioId
            _binding.nextButton.isEnabled = true
            var checkedId = -1
            when {
                _binding.optionOne.isChecked -> {
                    checkedId = 0
                }
                _binding.optionTwo.isChecked -> {
                    checkedId = 1
                }
                _binding.optionThree.isChecked -> {
                    checkedId = 2
                }
                _binding.optionFour.isChecked -> {
                    checkedId = 3
                }
                _binding.optionFive.isChecked -> {
                    checkedId = 4
                }
            }
            if(checkedId != -1) {
                QuestionKeeper.answered[value] = checkedId
                //println(checkedId)
            }

        }

        _binding.toolbar.setNavigationOnClickListener {
            fragmentHandller.prevQuestion(value-1)
        }


        if(value == 0) {
            _binding.previousButton.isEnabled = false
            _binding.toolbar.navigationIcon = null
        } else if (value == 4) {
            _binding.nextButton.text = "Submit"
        }
        _binding.toolbar.title = "Question ${value+1}"
        changeQuestion()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentHandller) {
            fragmentHandller = context
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(questionsNum: Int): FragmentQuiz {
            return FragmentQuiz(questionsNum)
        }
    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if(context)
//    }
}