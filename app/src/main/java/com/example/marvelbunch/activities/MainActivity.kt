package com.example.marvelbunch.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.marvelbunch.databinding.ActivityMainBinding
import com.example.marvelbunch.viewmodel.SharedViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        super.onCreate(savedInstanceState)
        setContentView(view)

        Handler(Looper.getMainLooper()).postDelayed({
          val intent = Intent(this@MainActivity, DashBoardActivity::class.java)
          startActivity(intent)
            finish()

        }, 2500)


    }


}