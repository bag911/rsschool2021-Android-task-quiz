package com.rsschool.quiz.quizfragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.rsschool.quiz.MainViewModel
import com.rsschool.quiz.R
import com.rsschool.quiz.databinding.FragmentFourthBinding
import com.rsschool.quiz.databinding.FragmentThirdBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FourthFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var _fourthBinding: FragmentFourthBinding ? = null
    private val fourthBinding get() = _fourthBinding!!

    private var listener: FragmentNavigation? = null

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
        _fourthBinding = FragmentFourthBinding.inflate(inflater,container,false)
        return fourthBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillText()
        checkRadioButtons()
        fourthBinding.previousBtn.setOnClickListener {
            listener?.goToThirdFrag()
            findNavController().navigate(R.id.action_fourthFragment_to_thirdFragment)
        }
        fourthBinding.nextBtn.setOnClickListener{
            listener?.goToFifthFrag()
            findNavController().navigate(R.id.action_fourthFragment_to_fifthFragment)
        }
    }

    private fun fillText() {
        val arrayOfAnswers = resources.getStringArray(R.array.answer4)
        fourthBinding.question.text = resources.getStringArray(R.array.questionsArray)[3]
        fourthBinding.optionOne.text = arrayOfAnswers[0]
        fourthBinding.optionTwo.text = arrayOfAnswers[1]
        fourthBinding.optionThree.text = arrayOfAnswers[2]
        fourthBinding.optionFour.text = arrayOfAnswers[3]
        fourthBinding.optionFive.text = arrayOfAnswers[4]
    }

    private fun checkRadioButtons() {
        if(mainViewModel.fourthAnswerId!=null) {
            view?.findViewById<RadioButton>(mainViewModel.fourthAnswerId!!)?.isChecked = true
            fourthBinding.nextBtn.isEnabled = true
        }
        fourthBinding.radioGroup.setOnCheckedChangeListener { radioGroup, i ->
            fourthBinding.nextBtn.isEnabled = true
            mainViewModel.fourthAnswer = view?.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)?.text.toString()
            mainViewModel.fourthAnswerId = radioGroup.checkedRadioButtonId
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _fourthBinding = null
    }
}