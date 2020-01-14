// Code Written by Rahul Sivadasan (11/01/2020)
package com.example.speech_to_text

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity() {



    private val ASK_FOR_SPEECH = 100



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

     // When Button is clicked it will show Speed conversion to text

        search_voice_btn.setOnClickListener{
            speak()
        }


    }
    private fun speak(){
        // I am adding an Intent here for the conversion to start
//------------------------------------------------------------------------------------------------------------------------------
        // RecognizerIntent :SpeechRecognizer is better for hands-free user interfaces,
        // since your app actually gets to respond to error conditions like "No matches" and perhaps restart itself.
        // When you use the Intent, the app beeps and shows a dialog that the user must press to continue.
//-------------------------------------------------------------------------------------------------------------------------------




        val speechIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)

        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,Locale.getDefault())
        speechIntent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Hello there! Say Something..")

        try{
            // If Error do not Happen it will show speech to text

            startActivityForResult(speechIntent,ASK_FOR_SPEECH)



        }catch (e:Exception){

            // If an Error Happens , The toast will be triggered

            Toast.makeText(this,e.message, Toast.LENGTH_SHORT).show()


        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            ASK_FOR_SPEECH -> {
                if (resultCode == Activity.RESULT_OK && null != data){
                    val result = data.getStringArrayExtra(RecognizerIntent.EXTRA_RESULTS)


                    textView.text = result[0]
                }
            }
    }
    }



}
