package com.example.arepaassembler

import android.app.Activity
import android.content.Intent
import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.arepaassembler.databinding.ActivityArepaBinding

class ArepaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArepaBinding

    lateinit var arepaNameTextView : TextView
    lateinit var feedbackEditText : EditText
    lateinit var arepaImage : ImageView
    private var arepaName : String? = null
    private var arepaLocation : String? = null
    private var arepaImageFilling : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityArepaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        //view
        arepaNameTextView = findViewById(R.id.arepaNameTextView)
        arepaImage = findViewById(R.id.arepaImage)

        //intent data
        arepaName = intent.getStringExtra("arepaName")
        arepaImageFilling = intent.getStringExtra("arepaImage")
        arepaLocation = intent.getStringExtra("arepaLocationUrl")

        arepaName?.let { Log.i("name received", it) };
        arepaLocation?.let { Log.i("location received", it) };

        arepaName?.let {arepaNameTextView.text = arepaName}
        arepaImageFilling?.let {
            when (arepaImageFilling) {
                "Chicken" -> arepaImage.setImageResource(R.drawable.polloguisado)
                "Pork" -> arepaImage.setImageResource(R.drawable.pabellon)
                "Plantain" -> arepaImage.setImageResource(R.drawable.original)
                else -> arepaImage.setImageResource(R.drawable.icon)
            }
        }

        binding.fab.setOnClickListener { view -> loadMaps() }
    }

    override fun onBackPressed() {
        val data = Intent()
        data.putExtra("feedback", feedbackEditText.text.toString())
        setResult(Activity.RESULT_OK, data) //must be set before super.onBackPressed()
        super.onBackPressed()
        finish()
    }

    private fun loadMaps(){ // Android Maps intent to open location in maps app https://developers.google.com/maps/documentation/urls/android-intents#kotlin
        // Create a Uri from an intent string. Use the result to create an Intent.
        val locationUrl = arepaLocation?.let{ Uri.parse(arepaLocation)}
        val gmmIntentUri = Uri.parse(locationUrl.toString())
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        mapIntent.resolveActivity(packageManager)?.let {
            startActivity(mapIntent)
        }
    }
}