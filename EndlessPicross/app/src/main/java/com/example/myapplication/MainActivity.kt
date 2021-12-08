package com.example.myapplication

import android.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.core.view.marginEnd
import com.google.android.material.slider.Slider
import java.util.*

class MainActivity : AppCompatActivity() {
//    private lateinit var sliderVal: Slider
    lateinit var grid: TableLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // views
//        sliderVal = findViewById(R.id.sizeSlider)
        grid = findViewById(R.id.gameGrid)

        // app setup
        generateGame()
    }

    private fun generateGame() {
        for(i in 0..5) {

        }
    }
    /*private fun setupBoard() {
        val boardSize:Int = sliderVal.value.toInt()
        for (i in 0..boardSize){
            val tempTableRow = TableRow(this)
            val params = TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT
            )
            params.setMargins(0, 0, 0, R.dimen.margin)
            params.weight = 1f
            tempTableRow.layoutParams = params
            for (j in 0..boardSize) {
                val tempButton = Button(this)
                val buttonParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
                )
                buttonParams.setMargins(0, 0, R.dimen.margin, 0)
                buttonParams.weight = 1f
                tempButton.setBackgroundResource(R.drawable.buttonshape)
                tempButton.layoutParams = buttonParams
                tempTableRow.addView(tempButton)
            }
            grid.addView(tempTableRow)
        }
    }*/
}