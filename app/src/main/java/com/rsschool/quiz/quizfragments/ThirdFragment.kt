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

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ThirdFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var listener: FragmentNavigation? = null

    private lateinit var thirdBinding: FragmentThirdBinding

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
        thirdBinding = FragmentThirdBinding.inflate(inflater,container,false)
        return thirdBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillText()
        checkRadioButtons()
        thirdBinding.previousBtn.setOnClickListener {
            listener?.goToSecondFrag()
        }
        thirdBinding.nextBtn.setOnClickListener{
            listener?.goToFourthFrag()
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
        if(mainViewModel.thirdAnswerId!=null) {
            view?.findViewById<RadioButton>(mainViewModel.thirdAnswerId!!)?.isChecked = true
            thirdBinding.nextBtn.isEnabled = true
        }
        thirdBinding.radioGroup.setOnCheckedChangeListener { radioGroup, i ->
            thirdBinding.nextBtn.isEnabled = true
            mainViewModel.thirdAnswer = view?.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)?.text.toString()
            mainViewModel.thirdAnswerId = radioGroup.checkedRadioButtonId
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