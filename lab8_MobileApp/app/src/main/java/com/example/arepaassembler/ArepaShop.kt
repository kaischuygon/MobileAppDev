package com.example.arepaassembler


data class ArepaShop(var name:String="", var locationUrl:String="") {
    fun suggestArepaShop(position:Int) {
        setArepaName(position)
        setArepaLocation(position)
    }

    private fun setArepaName(position: Int) {
        when (position) {
            0 -> name = "Pollo Guisado"
            1 -> name = "La Original"
            2 -> name = "Pabellon"
        }
    }

    private fun setArepaLocation(position: Int) {
        when (position) {
            0 -> locationUrl = ""
            1 -> locationUrl = ""
            2 -> locationUrl = ""
        }
    }
}