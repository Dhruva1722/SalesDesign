package com.example.salesdesign.Retrofit

import com.example.salesdesign.Activity.LoginResponse
import com.example.salesdesign.Activity.StockResponse
import com.example.salesdesign.Fragment.AttendanceData
import com.example.salesdesign.Fragment.Customer
import com.example.salesdesign.Fragment.Event
import com.example.salesdesign.Fragment.LocationInformation
import com.example.salesdesign.Fragment.MenuData
import com.example.salesdesign.Fragment.PurchaseTodayFood
import com.example.salesdesign.Fragment.PurchaseTomorrowFood
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService  {

// post data
    @POST("/emplogin")
    fun loginRequest(@Body credentials: JsonObject): Call<LoginResponse>

    @POST("/customer")
    fun saveCustomer(@Body customer: Customer): Call<Void>

    @POST("/attendance")
    fun saveAttendance (@Body attendanceData: AttendanceData): Call<Void>

    @POST("/menu/buy")
    fun buyTodayMenuItems(@Body purchaseData: PurchaseTodayFood): Call<Void>

    @POST("/menu/buy")
    fun buyTomorrowMenuItems(@Body purchaseData: PurchaseTomorrowFood): Call<Void>

    @Multipart
    @POST("/form")
    fun saveFormData(
        @Part("userId") userId: RequestBody,
        @Part("Transport_type") transportType: RequestBody,
        @Part("Fuel_in_liters") fuelInLiters: RequestBody,
        @Part("Food") food: RequestBody,
        @Part("Water") water: RequestBody,
        @Part("Hotel") hotel: RequestBody,
        @Part("Other_Transport") otherTransport: RequestBody,
        @Part image: MultipartBody.Part // Add RequestBody for the name
    ): Call<Void>



  // get data
    @GET("/customer")
    fun getCustomers(): Call<List<Customer>>

    @GET("/menu")
    fun getMenu(): Call<MenuData>

    @GET("/empdata")
    fun getStockData(): Call<List<StockResponse>>
    @GET("/event")
    fun getEvents(): Call<List<Event>>

//    @GET("/location/{id}")
//    fun getLocation(@Path("id") userId: String): Call<LocationResponse>

    @GET("/location/{id}")
    fun getLocationInfo(@Query("userId") userId: String): Call<List<LocationInformation>>
}