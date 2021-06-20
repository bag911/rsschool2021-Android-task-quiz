package com.rsschool.quiz

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var firstAnswer = ""
    var secondAnswer = ""
    var thirdAnswer = ""
    var fourthAnswer = ""
    var fifthAnswer = ""
    var firstAnswerId: Int? = null
    var secondAnswerId: Int? = null
    var thirdAnswerId: Int? = null
    var fourthAnswerId: Int? = null
    var fifthAnswerId: Int? = null

}