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
import com.rsschool.quiz.databinding.FragmentFourthBinding

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FifthFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var listener: FragmentNavigation? = null

    private lateinit var fifthBinding: FragmentFifthBinding

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
        fifthBinding = FragmentFifthBinding.inflate(inflater, container, false)
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