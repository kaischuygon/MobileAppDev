package com.example.randomizedpicross

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TableLayout
import android.widget.TableRow
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val grid = findViewById<TableLayout>(R.id.gameGrid)
        val width = 3
        val height = 3

        for(i in 0..height) {
            val newRow = TableRow(this)
            newRow.id = i
            grid.addView(newRow)
            for(j in 0..width) {
                val newButton = ImageButton(this)
                val colorValue = resources.getColor(R.color.black, null)
                newButton.setBackgroundColor(colorValue)
                newRow.addView(newButton)
            }
        }
    }
}