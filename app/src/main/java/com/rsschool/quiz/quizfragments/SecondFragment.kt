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


class SecondFragment : Fragment() {

    private var listener: FragmentNavigation? = null

    private var _secondBinding: FragmentSecondBinding? = null
    private val secondBinding get() = _secondBinding!!
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
        _secondBinding = FragmentSecondBinding.inflate(inflater, container, false)

        return secondBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fillText()
        checkRadioButtons()
        secondBinding.previousBtn.setOnClickListener {
            listener?.goToFirstFrag()
            findNavController().navigate(R.id.action_secondFragment_to_firstFragment)

        }
        secondBinding.nextBtn.setOnClickListener {
            listener?.goToThirdFrag()
            findNavController().navigate(R.id.action_secondFragment_to_thirdFragment)
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
        if (mainViewModel.secondAnswerId != null) {
            view?.findViewById<RadioButton>(mainViewModel.secondAnswerId!!)?.isChecked = true
            secondBinding.nextBtn.isEnabled = true
        }
        secondBinding.radioGroup.setOnCheckedChangeListener { radioGroup, i ->
            secondBinding.nextBtn.isEnabled = true
            mainViewModel.secondAnswer =
                view?.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)?.text.toString()
            mainViewModel.secondAnswerId = radioGroup.checkedRadioButtonId

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _secondBinding = null
    }


}