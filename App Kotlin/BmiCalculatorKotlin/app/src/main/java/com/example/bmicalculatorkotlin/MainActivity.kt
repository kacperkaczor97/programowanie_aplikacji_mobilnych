package com.example.bmicalculatorkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calButton = findViewById<Button>(R.id.buttonBMI)
        val graphButton = findViewById<Button>(R.id.buttonGraph)
        val quizButton = findViewById<Button>(R.id.buttonQuiz)

        calButton.setOnClickListener {
            val intent = Intent(this, Calculator::class.java)

            startActivity(intent)
        }
        graphButton.setOnClickListener {
            val intent = Intent(this, Graph::class.java)

                startActivity(intent)
            }
        quizButton.setOnClickListener {
            val intent = Intent(this, Quiz::class.java)

            startActivity(intent)
        }
    }
}