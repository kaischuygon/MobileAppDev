package com.example.myapplication

import android.annotation.SuppressLint
import android.app.ActionBar
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.view.marginEnd
import com.google.android.material.chip.Chip
import com.google.android.material.slider.Slider
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var grid: TableLayout
    lateinit var safeModeChip: Chip

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // views
        grid = findViewById(R.id.gameGrid)
        safeModeChip = findViewById(R.id.safeMode)

        // app setup
    }

    fun changeColorOnClick(view: View) {
        val currentColor = view.backgroundTintList?.defaultColor
        val defaultColor = getColor(R.color.design_default_color_primary)
        val clickedColor = Color.BLACK

        val nextColor = if (currentColor == clickedColor) defaultColor else clickedColor
        view.backgroundTintList = ColorStateList.valueOf(nextColor)
    }

    fun onButtonClick(view: View) {
        changeColorOnClick(view)

        Log.i("clickedButton: ", resources.getResourceEntryName(view.id))
    }

}