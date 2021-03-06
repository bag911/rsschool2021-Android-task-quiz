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
import com.rsschool.quiz.databinding.FragmentThirdBinding


class ThirdFragment : Fragment() {

    private var listener: FragmentNavigation? = null

    private var _thirdBinding: FragmentThirdBinding? = null
    private val thirdBinding get() = _thirdBinding!!
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as FragmentNavigation
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _thirdBinding = FragmentThirdBinding.inflate(inflater, container, false)
        return thirdBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillText()
        checkRadioButtons()
        thirdBinding.previousBtn.setOnClickListener {
            listener?.goToSecondFrag()
            findNavController().navigate(R.id.action_thirdFragment_to_secondFragment)
        }
        thirdBinding.nextBtn.setOnClickListener {
            listener?.goToFourthFrag()
            findNavController().navigate(R.id.action_thirdFragment_to_fourthFragment)
        }
    }

    private fun fillText() {
        val arrayOfAnswers = resources.getStringArray(R.array.answer3)
        thirdBinding.question.text = resources.getStringArray(R.array.questionsArray)[2]
        thirdBinding.optionOne.text = arrayOfAnswers[0]
        thirdBinding.optionTwo.text = arrayOfAnswers[1]
        thirdBinding.optionThree.text = arrayOfAnswers[2]
        thirdBinding.optionFour.text = arrayOfAnswers[3]
        thirdBinding.optionFive.text = arrayOfAnswers[4]
    }

    private fun checkRadioButtons() {
        if (mainViewModel.thirdAnswerId != null) {
            view?.findViewById<RadioButton>(mainViewModel.thirdAnswerId!!)?.isChecked = true
            thirdBinding.nextBtn.isEnabled = true
        }
        thirdBinding.radioGroup.setOnCheckedChangeListener { radioGroup, i ->
            thirdBinding.nextBtn.isEnabled = true
            mainViewModel.thirdAnswer =
                view?.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)?.text.toString()
            mainViewModel.thirdAnswerId = radioGroup.checkedRadioButtonId
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _thirdBinding = null
    }
}