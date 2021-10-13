package com.example.post

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

class UpdateDelete : AppCompatActivity() {
    lateinit var celname: EditText
    lateinit var hint1: EditText
    lateinit var hint2: EditText
    lateinit var hint3: EditText
    lateinit var update3: Button
    lateinit var delete3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_delete)



        celname = findViewById(R.id.celname)
        hint1 = findViewById(R.id.hint5)
        hint2 = findViewById(R.id.hint6)
        hint3 = findViewById(R.id.hint7)
        update3 = findViewById(R.id.upBu)
        delete3 = findViewById(R.id.delBu)


        val pk  = intent.extras?.getInt("pk")!!
        celname.setText(intent.extras?.getString("Cname")!!.toString())
        hint1.setText(intent.extras?.getString("hint1")!!.toString())
        hint2.setText(intent.extras?.getString("hint2")!!.toString())
        hint3.setText(intent.extras?.getString("hint3")!!.toString())


        update3.setOnClickListener {

        updateCel(pk, celname.text.toString(),hint1.text.toString(),hint2.text.toString(),hint3.text.toString())
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)


        }
        delete3.setOnClickListener {
            deleteUser(pk)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }


    private fun updateCel(pk :Int , name: String , hint1 : String ,hint2: String , hint3 : String) {


        val api = Retrofit.Builder()
            .baseUrl("https://dojo-recipes.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(apiInterface::class.java)


        CoroutineScope(Dispatchers.IO).launch {
            val response: Response<SItem?> = api.updateCName(pk, SItem(name,0,hint1,hint2,hint3) )!!.awaitResponse()
            if (response.isSuccessful) {
                val data = response.body()!!
                Log.d("dataUpdate", "Success : " + data.toString())
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@UpdateDelete, " Celebritie Updated ! ", Toast.LENGTH_SHORT).show()

                }

            } else {
                Log.d("dataUpdate", "failed !")

            }


        }
    }



    private fun deleteUser(pk : Int) {


        val api = Retrofit.Builder()
            .baseUrl("https://dojo-recipes.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(apiInterface::class.java)


        CoroutineScope(Dispatchers.IO).launch {
            val response: Response<SItem?> = api.deletecelebritie(pk)!!.awaitResponse()
            if (response.isSuccessful) {
//                val data = response.body()!!
              //  Log.d("dataDeleted", "Success : " + data.toString())
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@UpdateDelete, "Celebritie deleted !", Toast.LENGTH_SHORT).show()


                }

            } else {
                Log.d("dataDeleted", "failed !")

            }


        }
    }
}


