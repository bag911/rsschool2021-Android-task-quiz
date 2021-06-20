package com.rsschool.quiz.quizfragments

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toolbar
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.AppBarLayout
import com.rsschool.quiz.MainViewModel
import com.rsschool.quiz.R
import com.rsschool.quiz.databinding.FragmentFirstBinding



class FirstFragment : Fragment() {
    private var listener: FragmentNavigation? = null

    private var _firstBinding: FragmentFirstBinding? = null
    private val firstBinding get() = _firstBinding!!

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = context as FragmentNavigation
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _firstBinding = FragmentFirstBinding.inflate(inflater, container, false)
        return firstBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillText()
        checkRadioButtons()
        firstBinding.nextBtn.setOnClickListener {
            listener?.goToSecondFrag()
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }
    }

    private fun fillText() {
        val arrayOfAnswers = resources.getStringArray(R.array.answer1)
        firstBinding.question.text = resources.getStringArray(R.array.questionsArray)[0]
        firstBinding.optionOne.text = arrayOfAnswers[0]
        firstBinding.optionTwo.text = arrayOfAnswers[1]
        firstBinding.optionThree.text = arrayOfAnswers[2]
        firstBinding.optionFour.text = arrayOfAnswers[3]
        firstBinding.optionFive.text = arrayOfAnswers[4]
    }

    private fun checkRadioButtons() {
        if(mainViewModel.firstAnswerId!=null) {
            view?.findViewById<RadioButton>(mainViewModel.firstAnswerId!!)?.isChecked = true
            firstBinding.nextBtn.isEnabled = true
        }
        firstBinding.radioGroup.setOnCheckedChangeListener { radioGroup, i ->
            firstBinding.nextBtn.isEnabled = true
            mainViewModel.firstAnswer = view?.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)?.text.toString()
            mainViewModel.firstAnswerId = radioGroup.checkedRadioButtonId
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _firstBinding = null
    }


}