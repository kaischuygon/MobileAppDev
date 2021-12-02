package com.example.arepaassembler

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.switchmaterial.SwitchMaterial
import android.content.Intent
import android.net.Uri

class ArepaActivity : AppCompatActivity() {
    // private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityArepaBinding

    lateinit var arepaNameTextView : TextView
    lateinit var feedbackEditText : EditText
    private var arepaName : String? = null
    private var arepaLocation : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityArepaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

//        val navController = findNavController(R.id.nav_host_fragment_content_taco)
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)

        //view
        arepaNameTextView = findViewById(R.id.arepaNameTextView)
        feedbackEditText = findViewById(R.id.feedbackEditText)

        //intent data
        arepaName = intent.getStringExtra("tacoShopName")
        arepaLocation = intent.getStringExtra("tacoShopURL")

        arepaName?.let { Log.i("shop received", it) };
        arepaLocation?.let { Log.i("url received", it) };

        arepaLocation?.let {arepaLocation.text = "You are getting an $arepaName "}

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
        //create intent
//        var intent = Intent()
//        intent.action = Intent.ACTION_VIEW
//        intent.data = locationUrl?.let{ Uri.parse(locationUrl)}

        // Create a Uri from an intent string. Use the result to create an Intent.
        val locationUrl = arepaLocation?.let{ Uri.parse(arepaLocation)}
        val gmmIntentUri = Uri.parse(locationUrl.toString())

        // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        // Make the Intent explicit by setting the Google Maps package
        mapIntent.setPackage("com.google.android.apps.maps")

        // Attempt to start an activity that can handle the Intent
        startActivity(mapIntent)

        // start activity
//        startActivity(intent)
    }
}