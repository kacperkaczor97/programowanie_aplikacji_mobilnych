package com.example.bmicalculatorkotlin

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ir.farshid_roohi.linegraph.ChartEntity
import kotlinx.android.synthetic.main.activity_graph.*

class Graph : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph)

        val firstChartEntity = ChartEntity(Color.WHITE, graph1)

        val list = ArrayList<ChartEntity>()
        list.add(firstChartEntity)
        lineChart.legendArray = legendArr
//        lineChart.legendArray = valueArr
        lineChart.setList(list)

    }

    private val graph1 = floatArrayOf(1868297f, 1889360f, 1906632f, 1917527f, 1931921f, 1956974f, 1984248f, 2010244f, 2058550f, 2073129f, 2089869f)
    private val legendArr = arrayOf("05/12", "05/13", "05/14", "05/15", "05/16", "05/17", "05/18", "05/19", "05/20", "05/21", "05/22")
//    private val valueArr = arrayOf("1000000f", "2000000f", "3000000f", "4000000f", "5000000f", "6000000f", "1000000f", "1000000f", "1000000f", "1000000f", "1000000f")

}

