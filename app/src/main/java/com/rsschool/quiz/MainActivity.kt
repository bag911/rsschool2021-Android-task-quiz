package com.rsschool.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.rsschool.quiz.databinding.ActivityMainBinding
import com.rsschool.quiz.quizfragments.*
import javax.xml.namespace.NamespaceContext

class MainActivity : AppCompatActivity(), FragmentNavigation {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
//
//        val host = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
//        val navController = host.navController
//        val appBarConfiguration = AppBarConfiguration(navController.graph)
//        binding.toolbar.setupWithNavController(navController,appBarConfiguration)
    }

    fun openFirstFragment(){
        window.statusBarColor = resources.getColor(R.color.title_text)
        setTheme(R.style.Theme_Quiz_First)
        val firstFragment = FirstFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView,firstFragment).commit()
    }

    fun openSecondFragment(){
        window.statusBarColor = resources.getColor(R.color.title_text_second)
        val secondFragment = SecondFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView,secondFragment,"2").commit()
    }

    fun openThirdFragment(){
        window.statusBarColor = resources.getColor(R.color.title_text_third)
        val thirdFragment = ThirdFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView,thirdFragment,"3").commit()
    }

    fun openFourthFragment(){
        window.statusBarColor = resources.getColor(R.color.title_text_fourth)
        val fourthFragment = FourthFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView,fourthFragment,"4").commit()
    }

    fun openFifthFragment(){
        window.statusBarColor = resources.getColor(R.color.title_text_fifth)
        val fifthFragment = FifthFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView,fifthFragment,"5").commit()
    }

    private fun openResultFragment() {
        val resultFragment = ResultFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView,resultFragment).commit()

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

    override fun shareResults() {
       // TODO("Not yet implemented")
    }

    override fun backPress(frag:Int) {
        onBackPressed()
    }

    override fun onBackPressed() {
        super.onBackPressed()
//        val fragment2 = supportFragmentManager.findFragmentByTag("2")
//        val fragment3 = supportFragmentManager.findFragmentByTag("3")
//        val fragment4 = supportFragmentManager.findFragmentByTag("4")
//        val fragment5 = supportFragmentManager.findFragmentByTag("5")
//        when {
//            fragment2!!.isVisible -> openFirstFragment()
//            fragment3!!.isVisible -> openSecondFragment()
//            fragment4!!.isVisible -> openThirdFragment()
//            fragment5!!.isVisible -> openFourthFragment()
//            else -> super.onBackPressed()
        }
    }

}