package com.rsschool.quiz.quizfragments

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.AppBarLayout
import com.rsschool.quiz.MainViewModel
import com.rsschool.quiz.R
import com.rsschool.quiz.databinding.FragmentFirstBinding
import com.rsschool.quiz.databinding.FragmentSecondBinding

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var listener: FragmentNavigation? = null

    private lateinit var secondBinding: FragmentSecondBinding

    private val mainViewModel:MainViewModel by activityViewModels()

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
        // Inflate the layout for this fragment
        secondBinding = FragmentSecondBinding.inflate(inflater,container,false)

        return secondBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fillText()
        checkRadioButtons()
        secondBinding.previousBtn.setOnClickListener {
            listener?.goToFirstFrag()
        }
        secondBinding.nextBtn.setOnClickListener{
            listener?.goToThirdFrag()
        }
    }

    private fun fillText() {
        val arrayOfAnswers = resources.getStringArray(R.array.answer2)
        secondBinding.question.text = resources.getStringArray(R.array.questionsArray)[1]
        secondBinding.optionOne.text = arrayOfAnswers[0]
        secondBinding.optionTwo.text = arrayOfAnswers[1]
        secondBinding.optionThree.text = arrayOfAnswers[2]
        secondBinding.optionFour.text = arrayOfAnswers[3]
        secondBinding.optionFive.text = arrayOfAnswers[4]
    }

    private fun checkRadioButtons() {
        if(mainViewModel.secondAnswerId!=null) {
            view?.findViewById<RadioButton>(mainViewModel.secondAnswerId!!)?.isChecked = true
            secondBinding.nextBtn.isEnabled = true
        }
        secondBinding.radioGroup.setOnCheckedChangeListener { radioGroup, i ->
            secondBinding.nextBtn.isEnabled = true
            mainViewModel.secondAnswer = view?.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)?.text.toString()
            mainViewModel.secondAnswerId = radioGroup.checkedRadioButtonId

        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FirstFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}