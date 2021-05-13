package com.example.bmicalculatorkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_calculator.*

class Calculator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        buttonResult.setOnClickListener{
            val h = (editHeight.text).toString().toFloat()/100
            val w = (editWeight.text).toString().toFloat()
            val res = w/(h*h)
            textResult.text = "%.2f".format(res)
            buttonResultEat.setOnClickListener{
                if(res > 25 && res < 30) {
                    textEat.text = "Lekka Sałatka z kurczakiem i warzywami"
                }
                else if(res > 15 && res < 25){
                    textEat.text = "Kurczak z frytkami z batatow"
                }
                else if(res > 10 && res < 15){
                    textEat.text = "Nalesniki z Nuttela i owocami"
                }
                else{
                    textEat.text = "Podwojny burger z wolowina"
                }

            }
            buttonResultMen.setOnClickListener {
                val h = (editHeight.text).toString().toFloat()/100
                val w = (editWeight.text).toString().toFloat()
                val a = (editAge.text).toString().toInt()
                val res = 655.1 + (9.563*w) + (1.85*h) - (4.676*a)
                textKalcMen.text = "%.2f".format(res)
            }

            buttonResultWoman.setOnClickListener {
                val h = (editHeight.text).toString().toFloat()/100
                val w = (editWeight.text).toString().toFloat()
                val a = (editAge).text.toString().toInt()
                val res = 66.47 + 13.7*w + 5*w + 6.76*a
                textKalcWomen.text = "%.2f".format(res)
            }
        }
    }




}