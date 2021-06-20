package com.rsschool.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.rsschool.quiz.databinding.ActivityMainBinding
import com.rsschool.quiz.quizfragments.FragmentNavigation


class MainActivity : AppCompatActivity(), FragmentNavigation {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
//
        val host =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = host.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

    }

    fun openFirstFragment() {
        window.statusBarColor = resources.getColor(R.color.title_text)
        setTheme(R.style.Theme_Quiz_First)
        binding.appBarLayout.setBackgroundColor(resources.getColor(R.color.secondary_text))
        binding.toolbarTitle.text = getString(R.string.question_1)
        binding.appBarLayout.visibility = View.VISIBLE

    }

    fun openSecondFragment() {
        window.statusBarColor = resources.getColor(R.color.title_text_second)
        setTheme(R.style.Theme_Quiz_Second)
        binding.appBarLayout.setBackgroundColor(resources.getColor(R.color.secondary_text_second))
        binding.toolbarTitle.text = getString(R.string.question_2)
        binding.appBarLayout.visibility = View.VISIBLE

    }

    fun openThirdFragment() {
        window.statusBarColor = resources.getColor(R.color.title_text_third)
        setTheme(R.style.Theme_Quiz_Third)
        binding.appBarLayout.setBackgroundColor(resources.getColor(R.color.secondary_text_third))
        binding.toolbarTitle.text = getString(R.string.question_3)
        binding.appBarLayout.visibility = View.VISIBLE

    }

    fun openFourthFragment() {
        window.statusBarColor = resources.getColor(R.color.title_text_fourth)
        setTheme(R.style.Theme_Quiz_Fourth)
        binding.appBarLayout.setBackgroundColor(resources.getColor(R.color.secondary_text_fourth))
        binding.toolbarTitle.text = getString(R.string.question_4)
        binding.appBarLayout.visibility = View.VISIBLE

    }

    fun openFifthFragment() {
        window.statusBarColor = resources.getColor(R.color.title_text_fifth)
        setTheme(R.style.Theme_Quiz_Fifth)
        binding.appBarLayout.setBackgroundColor(resources.getColor(R.color.secondary_text_fifth))
        binding.toolbarTitle.text = getString(R.string.question_5)
        binding.appBarLayout.visibility = View.VISIBLE
    }

    private fun openResultFragment() {
        binding.appBarLayout.visibility = View.GONE

    }

    override fun goToFirstFrag() {
        openFirstFragment()
    }

    override fun goToSecondFrag() {
        openSecondFragment()
    }

    override fun goToThirdFrag() {
        openThirdFragment()
    }

    override fun goToFourthFrag() {
        openFourthFragment()
    }

    override fun goToFifthFrag() {
        openFifthFragment()
    }

    override fun goToResultFrag() {
        openResultFragment()
    }

    override fun restartAll() {
        finish()
        startActivity(intent)
    }


}