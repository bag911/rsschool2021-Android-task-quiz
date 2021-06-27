package com.rsschool.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.rsschool.quiz.databinding.ActivityMainBinding
import com.rsschool.quiz.quizfragments.FragmentNavigation


class MainActivity : AppCompatActivity(), FragmentNavigation {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val host =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = host.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    override fun onSupportNavigateUp(): Boolean {

        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    fun openFirstFragment() {
        window.statusBarColor = resources.getColor(R.color.title_text)
        setTheme(R.style.Theme_Quiz_First)
    }

    fun openSecondFragment() {
        window.statusBarColor = resources.getColor(R.color.title_text_second)
        setTheme(R.style.Theme_Quiz_Second)

    }

    fun openThirdFragment() {
        window.statusBarColor = resources.getColor(R.color.title_text_third)
        setTheme(R.style.Theme_Quiz_Third)
    }

    fun openFourthFragment() {
        window.statusBarColor = resources.getColor(R.color.title_text_fourth)
        setTheme(R.style.Theme_Quiz_Fourth)

    }

    fun openFifthFragment() {
        window.statusBarColor = resources.getColor(R.color.title_text_fifth)
        setTheme(R.style.Theme_Quiz_Fifth)
    }

    private fun openResultFragment() {
        binding.toolbar.visibility = View.GONE

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