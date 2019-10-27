package com.example.weather

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.w3c.dom.Text
import java.io.File
import java.io.FileOutputStream
import java.io.PrintWriter
import java.net.HttpURLConnection
import java.net.URL
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T








class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sendGet()
        val btn_click_me = findViewById<Button>(R.id.button)
        btn_click_me.setOnClickListener {
            // your code to perform when the user clicks on the button
            sendGet()
            Toast.makeText(this@MainActivity, "You clicked me.", Toast.LENGTH_SHORT).show()
        }
                }
    fun sendGet() {
        val textView = findViewById<TextView>(R.id.text_view)
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://www.tempo.pt/porto.htm"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                // Display the first 500 characters of the response string.
                textView.text = "Response is: ${response.substring(0, 500)}"
            writeResponse(response)
            },
            Response.ErrorListener { textView.text = "That didn't work!" }
        )

        queue.add(stringRequest)
    }

    fun writeResponse(response: String){

        val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        requestPermissions(permissions, 0x512)

        val dir   = File("/sdcard/Weather/")
        val file  = File(dir.toString() + "Response.xml")
        Log.d("Paths", dir.toString())

        if (dir.exists()){
            file.writeText(response)
            Log.d("File","Written to Response to File")
            parseResponse()
        }
        else {
            Log.d("File", "Created File")
            dir.mkdirs()
            writeResponse(response)
        }

    }
}
    fun parseResponse(){

    }
