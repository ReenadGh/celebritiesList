package com.example.post

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

class addCel : AppCompatActivity() {

    lateinit var celName : EditText
    lateinit var hint1: EditText
    lateinit var hint2: EditText
    lateinit var hint3: EditText

    lateinit var save: Button
    lateinit var view: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_cel)
        celName = findViewById(R.id.Celname)
        hint1 = findViewById(R.id.hint1)
        hint2 = findViewById(R.id.hint2)
        hint3 = findViewById(R.id.hint3)

        save = findViewById(R.id.save)
        view = findViewById(R.id.view)



        save.setOnClickListener {

            if (celName.text.toString().isNotEmpty() && hint1.text.toString().isNotEmpty()&& hint2.text.toString().isNotEmpty()&& hint3.text.toString().isNotEmpty()) {
                addCel(celName.text.toString(),hint1.text.toString(),hint2.text.toString(),hint3.text.toString())
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {

                Toast.makeText(this, "Fill all fields !! ", Toast.LENGTH_SHORT).show()


            }


        }
        view.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }


    }


    private fun addCel(name: String, hint1: String , hint2: String, hint3: String) {


        val api = Retrofit.Builder()
            .baseUrl("https://dojo-recipes.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(apiInterface::class.java)


        CoroutineScope(Dispatchers.IO).launch {
            val response: Response<SItem?> = api.addcelebritie(SItem(name,0,hint1,hint2,hint3))!!.awaitResponse()
            if (response.isSuccessful) {
                val data = response.body()!!
                Log.d("dataPush", "Success : " + data.toString())
                withContext(Dispatchers.Main) {
                    //Toast.makeText(this@addCel, " added !", Toast.LENGTH_SHORT).show()


                }

            } else {
                Log.d("dataPush", "failed !")

            }


        }
    }
}


