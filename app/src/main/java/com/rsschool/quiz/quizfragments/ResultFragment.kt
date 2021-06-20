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

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var resultBinding: FragmentResultBinding

    private var listener:FragmentNavigation? = null

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as FragmentNavigation
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        resultBinding = FragmentResultBinding.inflate(inflater,container,false)
        return resultBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val res = compareAnswers()
        "Your result is ${res}%".also { resultBinding.textView.text = it }

        Log.d("radioB","1 - ${mainViewModel.firstAnswer}; " +
                "2 - ${mainViewModel.secondAnswer}; " +
                "3 - ${mainViewModel.thirdAnswer}; " +
                "4 - ${mainViewModel.fourthAnswer}; " +
                "5 - ${mainViewModel.fifthAnswer}; ")
        resultBinding.closeBtn.setOnClickListener{
            activity?.finish()
        }
        resultBinding.shareBtn.setOnClickListener{
            val arrayOfQuestions = resources.getStringArray(R.array.questionsArray)
            val i = Intent(Intent.ACTION_SEND)
                .setType("plain/text")
                .putExtra(Intent.EXTRA_TEXT,"${arrayOfQuestions[0]}: \n${mainViewModel.firstAnswer}; \n\n" +
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ResultFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ResultFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}