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
    lateinit var image : ImageView
    lateinit var createButton : Button
    private var fillingId = -1
    private var myArepaShop = ArepaShop();
    private var selectedLocationPosition = 0
//    private val REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        fillingId = radioGroup.checkedRadioButtonId
        messageTextView = findViewById<TextView>(R.id.messageTextView)
        layoutRoot = findViewById<ConstraintLayout>(R.id.root_layout)
        checkBox1 = findViewById<CheckBox>(R.id.checkBox1)
        checkBox2 = findViewById<CheckBox>(R.id.checkBox2)
        checkBox3 = findViewById<CheckBox>(R.id.checkBox3)
        checkBox4 = findViewById<CheckBox>(R.id.checkBox4)
        checkboxes = arrayListOf<CheckBox>(checkBox1, checkBox2, checkBox3, checkBox4)
        spinner = findViewById<Spinner>(R.id.spinner)
        switch = findViewById<SwitchMaterial>(R.id.takeoutSwitch)
        image = findViewById<ImageView>(R.id.arepaImage)
        createButton = findViewById<Button>(R.id.createButton)

        //event listener
        createButton.setOnClickListener {
            selectedLocationPosition = spinner.selectedItemPosition
            myArepaShop.suggestArepaShop(selectedLocationPosition)
            Log.i("shop suggested", myArepaShop.name);
            Log.i("url suggested", myArepaShop.url);

            //create intent
            val intent = Intent(this, ArepaActivity::class.java)
            intent.putExtra("arepaName", myArepaShop.name)
            intent.putExtra("arepaLocation", myArepaShop.url)

            startActivity(intent)
        }
    }
    fun createTaco(view: View) {
        var filling : CharSequence = ""
        var toppingList : String = "" //String

        if (fillingId == -1){
            //SnackBar
            val fillingSnackBar = Snackbar.make(layoutRoot, "Please select a filling", Snackbar.LENGTH_SHORT)
            fillingSnackBar.show()
        } else {
            filling = findViewById<RadioButton>(fillingId).text
            when(filling) {
                "Chicken" -> image.setImageResource(R.drawable.polloguisado)
                "Pork" -> image.setImageResource(R.drawable.pabellon)
                "Plantain" -> image.setImageResource(R.drawable.original)
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