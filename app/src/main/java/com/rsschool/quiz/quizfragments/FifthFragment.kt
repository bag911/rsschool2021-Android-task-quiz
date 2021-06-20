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
import com.rsschool.quiz.databinding.FragmentFifthBinding
import com.rsschool.quiz.databinding.FragmentFirstBinding
import com.rsschool.quiz.databinding.FragmentFourthBinding




class FifthFragment : Fragment() {

    private var listener: FragmentNavigation? = null

    private var _fifthBinding: FragmentFifthBinding? = null
    private val fifthBinding get() = _fifthBinding!!

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as FragmentNavigation
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fifthBinding = FragmentFifthBinding.inflate(inflater, container, false)
        return fifthBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillText()
        checkRadioButtons()
        fifthBinding.previousBtn.setOnClickListener {
            listener?.goToFourthFrag()
            findNavController().navigate(R.id.action_fifthFragment_to_fourthFragment)
        }
        fifthBinding.nextBtn.setOnClickListener {
            listener?.goToResultFrag()
            findNavController().navigate(R.id.action_fifthFragment_to_resultFragment)
        }
    }

    private fun fillText() {
        val arrayOfAnswers = resources.getStringArray(R.array.answer5)
        fifthBinding.question.text = resources.getStringArray(R.array.questionsArray)[4]
        fifthBinding.optionOne.text = arrayOfAnswers[0]
        fifthBinding.optionTwo.text = arrayOfAnswers[1]
        fifthBinding.optionThree.text = arrayOfAnswers[2]
        fifthBinding.optionFour.text = arrayOfAnswers[3]
        fifthBinding.optionFive.text = arrayOfAnswers[4]
    }

    private fun checkRadioButtons() {
        if(mainViewModel.fifthAnswerId!=null) {
            view?.findViewById<RadioButton>(mainViewModel.fifthAnswerId!!)?.isChecked = true
            fifthBinding.nextBtn.isEnabled = true
        }
        fifthBinding.radioGroup.setOnCheckedChangeListener { radioGroup, i ->
            fifthBinding.nextBtn.isEnabled = true
            mainViewModel.fifthAnswer = view?.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)?.text.toString()
            mainViewModel.fifthAnswerId = radioGroup.checkedRadioButtonId
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _fifthBinding = null
    }

}