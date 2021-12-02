package com.example.arepaassembler

data class ArepaShop(var name:String="", var locationUrl:String="") {
    fun compileOrder(locationPosition:Int, arepaType:String){
        setArepaName(arepaType)
        setArepaLocation(locationPosition)
    }

    private fun setArepaName(arepaType: String) {
        when(arepaType) {
            "Chicken" -> name = "Pollo Guisado"
            "Pork" -> name = "Pabellon"
            "Plantain" -> name = "La Original"
        }
    }

    private fun setArepaLocation(position: Int) {
        when (position) {
            0 -> locationUrl = "geo:10.4686988,-67.0304536?q=arepa%20near%20caracas" // caracas
            1 -> locationUrl = "geo:10.6338511,-71.8170311?q=arepa%20near%20maricabo" // maricabo
            2 -> locationUrl = "geo:10.1727434,-68.0642647?q=arepa%20near%20valencia" // valencia
        }
    }
}