package com.example.learnandroidtvsecondstep.ui

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.learnandroidtvsecondstep.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)
        addFragment()
    }

    override fun onPause() {
        super.onPause()
//        android.os.Process.killProcess(android.os.Process.myPid())
    }

    private fun addFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frm_holder, MainFragment.newInstance())
        fragmentTransaction.commit()
    }
}