package com.example.myapplication

class Picross(difficulty: Int) {
    private var solution: Array<IntArray>
    private var currentBoard: Array<IntArray>
    var boardSize:Int = difficulty
    private var rowHints: Array<IntArray>
    private var colHints: Array<IntArray>

    init {
        solution = generateSolution()
        currentBoard = Array(difficulty) { IntArray(difficulty) {0} } // Initialize an empty game board
        rowHints = generateRowHints()
        colHints = generateColHints()
    }

    private fun generateSolution(): Array<IntArray> {
        val result = Array<IntArray>(boardSize) { IntArray(boardSize) }
        for (i in 0 until boardSize) {
            for(j in 0 until boardSize) {
                result[i][j] = (0..1).random()
            }
        }
        return result
    }

    fun getTileAt(i:Int, j:Int):Int {
        return currentBoard[i][j]
    }

    fun setTileAt(i:Int, j: Int, value:Int) {
        currentBoard[i][j] = value
    }

    fun printBoard():String { // for debugging
       var board = " \n"
        for (i in 0 until boardSize) {
            for (j in 0 until boardSize) {
                board += " " + currentBoard[i][j].toString()
            }
            board += "\n"
        }
        return "\n" + board
    }

    fun printSolution():String { // for debugging
        var board = " \n"
        for (i in 0 until boardSize) {
            for (j in 0 until boardSize) {
                board += " " + solution[i][j].toString()
            }
            board += "\n"
        }
        return "\n" + board
    }

    fun matchesSolution():Boolean {
        return solution contentDeepEquals currentBoard // content deep equals compares 2 arrays https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/content-deep-equals.html
    }

    private fun generateRowHints():Array<IntArray> {
        val temp : MutableList<IntArray> = arrayListOf()
        for(i in 0 until boardSize) {
            var consecutiveCount = 0
            var result: IntArray = intArrayOf()
            val mutableResult:MutableList<Int> = result.toMutableList()
            for(j in 0 until boardSize) {
                if (solution[i][j] == 1) consecutiveCount += 1
                else {
                    if(consecutiveCount != 0)
                        mutableResult.add(consecutiveCount)
                    consecutiveCount = 0
                }
            }
            if(consecutiveCount != 0) mutableResult.add(consecutiveCount)
            result = mutableResult.toIntArray()
            temp.add(result)
        }
        return temp.toTypedArray()
    }

    private fun generateColHints():Array<IntArray> {
        val temp : MutableList<IntArray> = arrayListOf()
        for(i in 0 until boardSize) {
            var consecutiveCount = 0
            var result: IntArray = intArrayOf()
            val mutableResult:MutableList<Int> = result.toMutableList()
            for(j in 0 until boardSize) {
                if (solution[j][i] == 1) consecutiveCount += 1
                else {
                    if(consecutiveCount != 0)
                        mutableResult.add(consecutiveCount)
                    consecutiveCount = 0
                }
            }
            if(consecutiveCount != 0) mutableResult.add(consecutiveCount)
            result = mutableResult.toIntArray()
            temp.add(result)
        }
        return temp.toTypedArray()
    }

    fun getHintsAtRow(rowIndex: Int):IntArray {
        return rowHints[rowIndex]
    }

    fun getHintsAtCol(colIndex: Int):IntArray {
        return colHints[colIndex]
    }
}