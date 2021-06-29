package com.rsschool.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.rsschool.quiz.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), FragmentHandller {
    private lateinit var binding: ActivityMainBinding
    var questionNumber = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        nextQuestion(0)
    }


    override fun openEndFragment() {
        val fragmentResult = FragmentResult.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragmentResult)
            .commit()
    }

    override fun nextQuestion(questionIndex: Int) {
        if(questionIndex != QuestionKeeper.questions.size) {
            val fragmentQuiz = FragmentQuiz.newInstance(questionIndex)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, fragmentQuiz)
                .commit()
            setThheme(questionIndex)
        } else {
            openEndFragment()
        }
    }

    override fun prevQuestion(questionIndex: Int) {
        val fragmentQuiz = FragmentQuiz.newInstance(questionIndex)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragmentQuiz)
            .commit()
        setThheme(questionIndex)
    }

    override fun close() {
        finish()
    }
    private fun setThheme(indx: Int) {
        when(indx) {
            0-> {
                setTheme(R.style.Theme_Quiz_First)
            }
            1-> {
                setTheme(R.style.Theme_Quiz_Second)
            }
            2-> {
                setTheme(R.style.Theme_Quiz_Third)
            }
            3-> {
                setTheme(R.style.Theme_Quiz_Fourth)
            }
            4-> {
                setTheme(R.style.Theme_Quiz_fifth)
            }
        }
    }
}