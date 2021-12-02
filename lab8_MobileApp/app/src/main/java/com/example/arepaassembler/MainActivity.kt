package com.example.arepaassembler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.switchmaterial.SwitchMaterial
import android.content.Intent

class MainActivity : AppCompatActivity() {
    //views
    lateinit var radioGroup : RadioGroup
    lateinit var messageTextView : TextView
    lateinit var layoutRoot : ConstraintLayout
    lateinit var checkBox1 : CheckBox
    lateinit var checkBox2 : CheckBox
    lateinit var checkBox3 : CheckBox
    lateinit var checkBox4 : CheckBox
    lateinit var checkboxes : ArrayList<CheckBox>
    lateinit var spinner : Spinner
    lateinit var switch : SwitchMaterial
    lateinit var createButton : Button
    private var fillingId = -1
    private var myArepaShop = ArepaShop();
    private var selectedLocationPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radioGroup = findViewById(R.id.radioGroup)
        messageTextView = findViewById(R.id.messageTextView)
        layoutRoot = findViewById(R.id.root_layout)
        checkBox1 = findViewById(R.id.checkBox1)
        checkBox2 = findViewById(R.id.checkBox2)
        checkBox3 = findViewById(R.id.checkBox3)
        checkBox4 = findViewById(R.id.checkBox4)
        checkboxes = arrayListOf(checkBox1, checkBox2, checkBox3, checkBox4)
        spinner = findViewById(R.id.spinner)
        switch = findViewById(R.id.takeoutSwitch)
        createButton = findViewById(R.id.createButton)

        //event listener
        createButton.setOnClickListener {
            //create intent
            if(readOptions().first != "") { // do not move onto next intent if options are not selected
                selectedLocationPosition = spinner.selectedItemPosition
                fillingId = radioGroup.checkedRadioButtonId
                val selectedRadioButton = findViewById<RadioButton>(fillingId)
                val selectedArepaPosition = selectedRadioButton.text
                myArepaShop.compileOrder(selectedLocationPosition, selectedArepaPosition.toString())
                Log.i("arepa created", myArepaShop.name);
                Log.i("url suggested", myArepaShop.locationUrl);
                val intent = Intent(this, ArepaActivity::class.java)
                intent.putExtra("arepaName", readOptions().first)
                intent.putExtra("arepaImage", readOptions().second)
                intent.putExtra("arepaLocationUrl", myArepaShop.locationUrl)

                startActivity(intent)
            }
        }
    }

    fun readOptions() : Pair<String, String> {
        fillingId = radioGroup.checkedRadioButtonId
        if (fillingId == -1) {
            //SnackBar
            val fillingSnackBar =
                Snackbar.make(layoutRoot, "Please select a filling", Snackbar.LENGTH_SHORT)
            fillingSnackBar.show()
        } else {
            var filling: CharSequence = findViewById<RadioButton>(fillingId).text
            var toppingList: String = ""
            var arepaMessage: String
            //checkboxes
            for (checkbox in checkboxes) {
                val newTopping: String = checkbox.text.toString()
                if (checkbox.isChecked) {
                    toppingList += " $newTopping"
                }
            }
            //spinner
            val location = spinner.selectedItem
            //textview
            arepaMessage = "You'd like an ${myArepaShop.name} arepa with $toppingList at $location"
            //switch
            if (!switch.isChecked) {
                arepaMessage += " for take out"
            }
            return Pair(arepaMessage, filling.toString())
        }
        return Pair("", "")
    }
}