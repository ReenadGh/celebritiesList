package com.example.post

import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.Body

import okhttp3.ResponseBody

import retrofit2.http.PUT




interface apiInterface {


    @POST("/celebrities/")
    fun addcelebritie(@Body celeb :SItem ) : Call<SItem?>

    @GET("/celebrities/")
    fun getSInfo() :Call <List<SItem>?>
   // @FormUrlEncoded
  //  @PUT("/test/{id}")
  //  fun updateUserName(@Path("id")id : Int , @Field("name") name : String): Call<UserItem?>

      @PUT("/celebrities/{id}")
     fun updateCName(@Path("id")id : Int ,  @Body celebritie :SItem): Call<SItem?>
    @DELETE("/celebrities/{id}")
    fun deletecelebritie(@Path("id")id : Int ): Call<SItem?>

}