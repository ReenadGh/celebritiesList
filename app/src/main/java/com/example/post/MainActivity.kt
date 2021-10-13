package com.example.post

import android.R.attr
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import android.R.attr.targetActivity
import android.text.Editable
import android.widget.EditText
import android.widget.SearchView


class MainActivity : AppCompatActivity() {



    lateinit var goToAddUser : Button
    lateinit var search  : Button
    lateinit var serachText : EditText
    var listofCel  = ArrayList<SItem>()
    var filltedCelList  = ArrayList<SItem>()
  lateinit var myRV : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showCInfo()
         myRV = findViewById<RecyclerView>(R.id.rvMain)
        goToAddUser = findViewById(R.id.goToAdduser)
        search = findViewById(R.id.search)
        serachText = findViewById(R.id.searchText)





        goToAddUser.setOnClickListener {
            val intent = Intent(this, addCel::class.java)
            startActivity(intent)

        }


        search.setOnClickListener {


            if(serachText.text.toString().isNotEmpty()){

                filltedCelList = listofCel.filter{ item ->  item.name.toLowerCase().contains(serachText.text.toString().toLowerCase())
                        || item.taboo1.toLowerCase().contains(serachText.text.toString().toLowerCase())
                        || item.taboo2.toLowerCase().contains(serachText.text.toString().toLowerCase())
                        || item.taboo3.toLowerCase().contains(serachText.text.toString().toLowerCase())
                } as ArrayList<SItem>
                myRV.adapter = RecyclerViewAdapter(this@MainActivity, filltedCelList)
                myRV.adapter!!.notifyDataSetChanged()
                myRV.layoutManager = LinearLayoutManager(this@MainActivity)


            }
            else{


                myRV.adapter = RecyclerViewAdapter(this@MainActivity, listofCel)
                myRV.adapter!!.notifyDataSetChanged()
                myRV.layoutManager = LinearLayoutManager(this@MainActivity)

            }

        }




    }


    private fun showCInfo() {



        val api = Retrofit.Builder()
            .baseUrl("https://dojo-recipes.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(apiInterface::class.java)


        CoroutineScope(Dispatchers.IO).launch {
            val response: Response<List<SItem>?> = api.getSInfo()!!.awaitResponse()
            if (response.isSuccessful) {
                val dataBody = response.body()!!
                Log.d("dataShow" , "Success :  " )




                withContext(Dispatchers.Main) {
                    listofCel = dataBody as ArrayList<SItem>
                    myRV.adapter = RecyclerViewAdapter(this@MainActivity, listofCel)
                    myRV.layoutManager = LinearLayoutManager(this@MainActivity)
                    myRV.adapter!!.notifyDataSetChanged()








                }

            }


            else {
                Log.d("dataShow" , "failed !")

            }


        }
    }





}