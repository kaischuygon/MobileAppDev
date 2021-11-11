package com.example.arepaassembler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.switchmaterial.SwitchMaterial

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun createTaco(view: View) {
        var filling : CharSequence = ""
        var toppingList : String = "" //String

        //views
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val fillingId =radioGroup.checkedRadioButtonId
        val messageTextView = findViewById<TextView>(R.id.messageTextView)
        val layoutRoot = findViewById<ConstraintLayout>(R.id.root_layout)
        val checkBox1 = findViewById<CheckBox>(R.id.checkBox1)
        val checkBox2 = findViewById<CheckBox>(R.id.checkBox2)
        val checkBox3 = findViewById<CheckBox>(R.id.checkBox3)
        val checkBox4 = findViewById<CheckBox>(R.id.checkBox4)
        val checkboxes = arrayListOf<CheckBox>(checkBox1, checkBox2, checkBox3, checkBox4)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val switch = findViewById<SwitchMaterial>(R.id.takeoutSwitch)
        val image = findViewById<ImageView>(R.id.arepaImage)

        if (fillingId == -1){
            //SnackBar
            val fillingSnackBar = Snackbar.make(layoutRoot, "Please select a filling", Snackbar.LENGTH_SHORT)
            fillingSnackBar.show()
        } else {
            filling = findViewById<RadioButton>(fillingId).text
            when(filling) {
                "Chicken" -> image.setImageResource(R.drawable.chicken)
                "Pork" -> image.setImageResource(R.drawable.pork)
                "Plantain" -> image.setImageResource(R.drawable.plantain)
            }

            //checkboxes
            for(checkbox in checkboxes) {
                val newTopping : String = checkbox.text.toString()
                if(checkbox.isChecked){
                    toppingList += " $newTopping"
                }
            }

            //spinner
            val location = spinner.selectedItem

            //textview
            messageTextView.text = "You'd like an $filling arepa with $toppingList at $location"

            //switch
            if (switch.isChecked) {
                messageTextView.text = "You'd like an $filling arepa with $toppingList at $location"
            } else {
                messageTextView.text = "You'd like an $filling arepa with $toppingList at $location for take out"
            }
        }
    }
}