package com.rsschool.quiz.quizfragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.activityViewModels
import com.google.android.material.appbar.AppBarLayout
import com.rsschool.quiz.MainViewModel
import com.rsschool.quiz.R
import com.rsschool.quiz.databinding.FragmentResultBinding


class ResultFragment : Fragment() {



    private var _resultBinding: FragmentResultBinding? = null
    private val resultBinding get() = _resultBinding!!

    private var listener:FragmentNavigation? = null

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as FragmentNavigation
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _resultBinding = FragmentResultBinding.inflate(inflater,container,false)
        return resultBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val res = compareAnswers()
        "Your result is ${res}%".also { resultBinding.textView.text = it }

        resultBinding.closeBtn.setOnClickListener{
            activity?.finish()
        }
        resultBinding.shareBtn.setOnClickListener{
            val arrayOfQuestions = resources.getStringArray(R.array.questionsArray)
            val i = Intent(Intent.ACTION_SEND)
                .setType("plain/text")
                .putExtra(Intent.EXTRA_TEXT,"Your result is ${res}%\n\n" +
                        "${arrayOfQuestions[0]}: \n${mainViewModel.firstAnswer}; \n\n" +
                        "${arrayOfQuestions[1]}: \n ${mainViewModel.secondAnswer}; \n\n" +
                        "${arrayOfQuestions[2]}: \n ${mainViewModel.thirdAnswer}; \n\n" +
                        "${arrayOfQuestions[3]}: \n ${mainViewModel.fourthAnswer}; \n\n" +
                        "${arrayOfQuestions[4]}: \n ${mainViewModel.fifthAnswer}; \n\n")
                .putExtra(Intent.EXTRA_EMAIL,"bag911@gmail.com")
            startActivity(i)

        }
        resultBinding.restartBtn.setOnClickListener{
            listener?.restartAll()
        }
    }

    private fun compareAnswers():Int {
        var result = 0
        if (mainViewModel.firstAnswer == resources.getStringArray(R.array.answer1)[1]) result += 20
        if (mainViewModel.secondAnswer == resources.getStringArray(R.array.answer2)[0]) result += 20
        if (mainViewModel.thirdAnswer == resources.getStringArray(R.array.answer3)[4]) result += 20
        if (mainViewModel.fourthAnswer == resources.getStringArray(R.array.answer4)[0]) result += 20
        if (mainViewModel.fifthAnswer == resources.getStringArray(R.array.answer5)[1]) result += 20
        return result
    }

    override fun onDestroy() {
        super.onDestroy()
        _resultBinding = null
    }


}