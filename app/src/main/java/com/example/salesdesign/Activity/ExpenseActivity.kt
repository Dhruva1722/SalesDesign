package com.example.salesdesign.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.example.salesdesign.MainActivity
import com.example.salesdesign.R
import com.example.salesdesign.Retrofit.ApiService
import com.example.salesdesign.Retrofit.RetrofitClient
import com.google.android.material.textfield.TextInputLayout
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class ExpenseActivity : AppCompatActivity() {

    private lateinit var backbtn : ImageView


    private lateinit var savebtn: Button

    private lateinit var radioGroup: RadioGroup
    private lateinit var busRadio: RadioButton
    private lateinit var bikeRadio: RadioButton
    private lateinit var trainRadio: RadioButton
    private lateinit var flightRadio: RadioButton
    private lateinit var uploadButton: Button
    private lateinit var foodInput : EditText
    private lateinit var waterInput :  EditText
    private lateinit var hotelInput :  EditText
    private lateinit var otherInput :  EditText

    private lateinit var sharedPreferences: SharedPreferences
    private var userId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense)


        radioGroup = findViewById(R.id.idRadioGroup)
//        uploadButton = findViewById(R.id.uploadButton)
        savebtn = findViewById(R.id.saveToDatabase)

        busRadio = findViewById(R.id.idBtnBusRadio)
        bikeRadio = findViewById(R.id.idBtnBikeRadio)
        trainRadio = findViewById(R.id.idBtnTrainRadio)
        flightRadio = findViewById(R.id.idBtnFlightRadio)
        foodInput = findViewById(R.id.foodExpensesId)
        waterInput = findViewById(R.id.waterExpenseId)
        hotelInput = findViewById(R.id.hotelExpenseId)
        otherInput = findViewById(R.id.otherExpensesId)

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        userId = sharedPreferences.getString("User", null) ?: ""

        val apiService = RetrofitClient.getClient().create(ApiService::class.java)


        savebtn.setOnClickListener {
            val Transport_type = when {
                busRadio.isChecked -> "Bus"
                bikeRadio.isChecked -> "Bike"
                trainRadio.isChecked -> "Train"
                flightRadio.isChecked -> "Flight"
                else -> ""
            }


            val Food = foodInput.editableText.toString()
            val Water = waterInput.editableText.toString()
            val Hotel = hotelInput.editableText.toString()
            val  Other_Transport = otherInput.editableText.toString()
            // Convert string values to RequestBody
            val userIdRequestBody = RequestBody.create(null, userId!!)
            val transportTypeRequestBody = RequestBody.create(null, Transport_type)
            val fuelInLitersRequestBody = RequestBody.create(null, "0")
            val foodRequestBody = RequestBody.create(null, Food)
            val waterRequestBody = RequestBody.create(null, Water)
            val hotelRequestBody = RequestBody.create(null, Hotel)
            val otherTransportRequestBody = RequestBody.create(null, Other_Transport)

            // Assuming this is inside your activity or fragment
            val imageResourceId = R.drawable.amico
            val imageBitmap = BitmapFactory.decodeResource(resources, imageResourceId)
            val imageFile = convertBitmapToFile(imageBitmap, "amico.png")
            val imageRequestBody = RequestBody.create("image/*".toMediaTypeOrNull(), imageFile)
            val imagePart = MultipartBody.Part.createFormData("image", imageFile.name, imageRequestBody)


            val requestData = "User ID: $userId\n" +
                    "Transport Type: $Transport_type\n" +
                    "Food: $Food\n" +
                    "Water: $Water\n" +
                    "Hotel: $Hotel\n" +
                    "Other Transport: $Other_Transport\n" +
                    "image data: $imagePart\n"

            Log.d("UserDetails", "Data being sent to API: ${requestData}")

            // Make the API request
            apiService.saveFormData(
                userIdRequestBody,
                transportTypeRequestBody,
                fuelInLitersRequestBody,
                foodRequestBody,
                waterRequestBody,
                hotelRequestBody,
                otherTransportRequestBody,
                imagePart
            ).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        // Handle a successful response
                        Toast.makeText(this@ExpenseActivity, "Data saved successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        // Handle an unsuccessful response
                        Toast.makeText(this@ExpenseActivity, "Failed to save data", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    // Handle the network error
                    Toast.makeText(this@ExpenseActivity, "Network error", Toast.LENGTH_SHORT).show()
                }
            })
        }





        backbtn = findViewById(R.id.backArrow)
        backbtn.setOnClickListener {
            val intent = Intent(this@ExpenseActivity, MainActivity::class.java)
            startActivity(intent)
        }


    }

    private fun convertBitmapToFile(bitmap: Bitmap, fileName: String): File {
        val file = File(cacheDir, fileName)
        file.createNewFile()

        // Convert bitmap to byte array
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()

        // Write the bytes in file
        val fileOutputStream = FileOutputStream(file)
        fileOutputStream.write(byteArray)
        fileOutputStream.flush()
        fileOutputStream.close()

        return file
    }


}


//data class ExpenseData(
//    val Transport_type: String,
//    val Fuel_in_liters: Int,
//    val Food: String,
//    val Water: String,
//    val Hotel: String,
//    val Other_Transport: String,
//    val images: List<String>,
//    val ImageName: String
//)