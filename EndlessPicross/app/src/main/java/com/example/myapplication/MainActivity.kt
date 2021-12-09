package com.example.myapplication

import android.R.attr
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
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginEnd
import com.google.android.material.chip.Chip
import com.google.android.material.slider.Slider
import java.util.*
import com.google.android.material.snackbar.Snackbar
import android.R.attr.button
import android.R.attr.layout_centerInParent
import android.view.Gravity
import androidx.core.view.marginBottom
import com.google.android.material.textfield.TextInputLayout
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {
    lateinit var grid: TableLayout
    lateinit var layout: ConstraintLayout
    lateinit var newGameButton: Button
    var difficulty = 5

    lateinit var currentGame: Picross // implements all game logic and stores current board

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // views
        grid = findViewById(R.id.gameGrid)
        layout = findViewById(R.id.root_layout)
        newGameButton = findViewById(R.id.newGameButton)

        // app setup
        newGame() // start a new game
        newGameButton.setOnClickListener { newGame() } // restart game on new game button click
    }

    private fun setHints() {
        for (i in 0 until currentGame.boardSize) {
            val rowsArray:IntArray = currentGame.getHintsAtRow(i)
            val colsArray:IntArray = currentGame.getHintsAtCol(i)
            val currentCol = (grid.getChildAt(0) as TableRow).getVirtualChildAt(i+1) as LinearLayout
            val currentRow = (grid.getChildAt(i+1) as TableRow).getVirtualChildAt(0) as LinearLayout
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            params.weight = 1f
            for (rowHintIndex in 0 until rowsArray.count()) {
                val tempRowHint = TextView(this)
                tempRowHint.text = rowsArray[rowHintIndex].toString()
                tempRowHint.setTextColor(resources.getColor(R.color.secondaryTextColor, theme))
                tempRowHint.gravity = Gravity.CENTER
                tempRowHint.layoutParams = params
                tempRowHint.setTextSize(TypedValue.COMPLEX_UNIT_SP,10F)
                currentRow.addView(tempRowHint)
            }
            for (colHintIndex in 0 until colsArray.count()) {
                val tempColHint = TextView(this)
                tempColHint.text = colsArray[colHintIndex].toString()
                tempColHint.setTextColor(resources.getColor(R.color.secondaryTextColor, theme))
                tempColHint.gravity = Gravity.CENTER
                tempColHint.layoutParams = params
                tempColHint.setTextSize(TypedValue.COMPLEX_UNIT_SP,10F)
                currentCol.addView(tempColHint)
            }
        }
    }

    fun onButtonClick(view: View) {
        val defaultColor = getColor(R.color.secondaryLightColor)
        val clickedColor = getColor(R.color.primaryColor)
        val nextColor: Int

        val row = resources.getResourceEntryName(view.id)[3].digitToInt()
        val col = resources.getResourceEntryName(view.id)[4].digitToInt()
        nextColor = if (currentGame.getTileAt(row, col) == 0) { // if unchecked
            currentGame.setTileAt(row, col, 1)
            clickedColor
        } else {
            currentGame.setTileAt(row, col, 0)
            defaultColor
        }
        view.backgroundTintList = ColorStateList.valueOf(nextColor) // update color on click
        isGameOver() // checks if game is over
    }

    private fun isGameOver() { // checks if game is over and if so, starts new game and lets user know
        if(currentGame.matchesSolution()) {
            Snackbar.make(layout, getString(R.string.snackbarFillingMessage), Snackbar.LENGTH_SHORT).show()
            Log.i("Game over: ", "Yes")
            newGame()
        }
    }

    private fun resetBoard() { // reset the color of grid
        val rowCount = grid.childCount
        for (i in 0 until rowCount) { // iterate through all rows
            val row = grid.getChildAt(i)
            if (row is TableRow) { // ensure child of grid is table row
                val rowChildCount = row.childCount
                for (j in 0 until rowChildCount) {
                    val v = row.getChildAt(j)
                    if (v is Button) v.backgroundTintList = ColorStateList.valueOf(getColor(R.color.secondaryLightColor)) // change color of all buttons in game grid
                    if (v is LinearLayout) v.removeAllViewsInLayout() // clear all hints
                }
            }
        }
    }

    private fun newGame() {
//        setupTiles()
        currentGame = Picross(difficulty) // this resets the entire game logic, including solution and current board
        Log.i("Solution: ", currentGame.printSolution())
        resetBoard()
        setHints() // set new hints
    }
}