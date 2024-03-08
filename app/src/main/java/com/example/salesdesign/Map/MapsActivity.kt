package com.example.salesdesign.Map

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.example.salesdesign.MainActivity
import com.example.salesdesign.R
import com.example.salesdesign.databinding.ActivityMapsBinding


import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.PolylineOptions
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.Locale

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var locationProvider: LocationProvider

    private lateinit var backbtn : ImageView

    private lateinit var binding: ActivityMapsBinding

    private val presenter = MapPresenter(this)

    private lateinit var originEdt: AutoCompleteTextView
    private lateinit var destinationEdt: AutoCompleteTextView
    private lateinit var submitBtn: Button

//    private lateinit var origin : String
//    private lateinit var destination : String

    private lateinit var geocoder: Geocoder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        geocoder = Geocoder(this)


        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        val autoCompleteLayout = findViewById<RelativeLayout>(R.id.autoCompleteLayout)
        val btnStartStop = findViewById<Button>(R.id.btnStartStop)
        originEdt = findViewById(R.id.originEdt)
        destinationEdt = findViewById(R.id.destinationEdt)

        val upArrow = findViewById<ImageView>(R.id.indicatorArrowUp)
        val downArrow = findViewById<ImageView>(R.id.indicatorArrowDown)
        val container = findViewById<View>(R.id.container)

        downArrow.setOnClickListener {
            if (container.visibility == View.VISIBLE) {
                container.visibility = View.GONE
                downArrow.visibility = View.GONE
                upArrow.visibility = View.VISIBLE
            } else {
                container.visibility = View.VISIBLE
                downArrow.visibility = View.VISIBLE
                upArrow.visibility = View.GONE
            }
        }

        upArrow.setOnClickListener {
            if (container.visibility == View.VISIBLE) {
                container.visibility = View.GONE
                downArrow.visibility = View.GONE
                upArrow.visibility = View.VISIBLE
            } else {
                container.visibility = View.VISIBLE
                downArrow.visibility = View.VISIBLE
                upArrow.visibility = View.GONE
            }
        }

        submitBtn = findViewById(R.id.submitBtn)
        submitBtn.setOnClickListener {
            autoCompleteLayout.visibility = View.GONE
            val origin = originEdt.text.toString()
            val destination = destinationEdt.text.toString()
            requestDirections(origin, destination)
            btnStartStop.visibility = View.VISIBLE
        }

        locationProvider = LocationProvider(this)

        geocoder = Geocoder(this, Locale.getDefault())


        // Set up the AutoCompleteTextView with the user's current location as the default value
        locationProvider.liveLocation.observe(this, { location ->
            // Update the origin EditText with the current location
            originEdt.setText("${location.latitude}, ${location.longitude}")
        })
        // Request the user's current location
        locationProvider.getUserLocation()

        binding.btnStartStop.setOnClickListener {
            if (binding.btnStartStop.text == getString(R.string.start_label)) {
                startTracking()
                binding.btnStartStop.setText(R.string.stop_label)
            } else {
                stopTracking()
                binding.btnStartStop.setText(R.string.start_label)
            }
        }

        presenter.onViewCreated()

        setUpAutoCompleteTextViews()


        backbtn = findViewById(R.id.backArrow)
        backbtn.setOnClickListener {
            val intent = Intent(this@MapsActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setUpAutoCompleteTextViews() {
        val ahmedabadAddresses = arrayOf(
            "Ahmedabad, Gujarat, India", "Gandhinagar, Gujarat, India", "Vastrapur, Ahmedabad, Gujarat, India",
            "Manek Chowk, Ahmedabad, Gujarat, India", "Ellis Bridge, Ahmedabad, Gujarat, India", "Iskcon temple, Ahmedabad", "Navrangpura , Ahmedabad",
            "Kathwada , Nikol , Ahmedabad", "Bapunagar Approach ,  Ahmedabad", "Jadeshwar van , Ahmedabad",
            "Vastral, Ahmedabad", "RTO Office , Vastral, Ahmedabad", "Vatva GIDC Phase 1, Ahmedabad",
            "Multispan Control Instrument Pvt Ltd , Vatva , Ahmedabad", "Isanpur , Ahmedabad",
            "Sarkhej Roza ,Ahmedabad", "LJ University , Ahmedabad",
            "Naroda , Ahmedabad,India", "CTM, Ahmedabad","Narol BRTS,Ahmedabad","Paldi,Ahmedabad", "Nehrunagar Ahmedabad","Law Garden , Ahmedabad",
            "Bopal , Ahmedabad", "Shela , Ahmedabad",
            "Ghodasar , Ahmedabad","Vinzol , Ahmedabad","Hathijan, Ahmedabad","Lambha , Ahmedabad","Jashodanagar Char Rasta","CIPET:IPT,Ahmedabad","Amraiwadi ,Ahmedabad",
            "Rabri Colony, Ahmedabad","Soni ni Chali, Ahmedabad","Rakhial Road, Ahmedabad","Thakkarnagar , Ahmedabad","Sabarmati Gandhi Ashram","Science City , Ahmedabad"
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, ahmedabadAddresses.map { it.toLowerCase(Locale.getDefault()) })
        originEdt.setAdapter(adapter)
        destinationEdt.setAdapter(adapter)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        locationProvider.initializeMap(map)

        val origin = originEdt.text.toString()
        val destination = destinationEdt.text.toString()
        requestDirections(origin, destination)

        presenter.ui.observe(this) { ui ->
            updateUi(ui)
        }
        presenter.onMapLoaded()
        map.uiSettings.isZoomControlsEnabled = true
    }

    private fun startTracking() {
        binding.container.txtPace.text = ""
        binding.container.txtDistance.text = ""
        binding.container.txtTime.base = SystemClock.elapsedRealtime()
        binding.container.txtTime.start()
//        map.clear()

        presenter.startTracking()
    }

    private fun stopTracking() {
        presenter.stopTracking()
        binding.container.txtTime.stop()
    }

    @SuppressLint("MissingPermission")
    private fun updateUi(ui: Ui) {
        if (ui.currentLocation != null && ui.currentLocation != map.cameraPosition.target) {
            map.isMyLocationEnabled = true
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(ui.currentLocation, 18f))
        }
        binding.container.txtDistance.text = ui.formattedDistance
        binding.container.txtPace.text = ui.formattedPace
        drawRoute(ui.userPath)
    }

    private fun drawRoute(locations: List<LatLng>) {
        val polylineOptions = PolylineOptions()

//    map.clear()
        val points = polylineOptions.points
        points.addAll(locations)

        map.addPolyline(polylineOptions)
    }

    private fun requestDirections(origin: String, destination: String) {

        val client = OkHttpClient()

//        val origin = originEdt.text.toString()
//        val destination = destinationEdt.text.toString()

        val startPointLatLng = getLatLngFromAddress(origin)
        val endPointLatLng = getLatLngFromAddress(destination)

        Log.d("--------", "requestDirections: ${startPointLatLng}  ${endPointLatLng}")

        if (startPointLatLng != null && endPointLatLng != null) {
            val (startPointLat, startPointLng) = startPointLatLng
            val (endPointLat, endPointLng) = endPointLatLng

            val url =
                "https://trueway-directions2.p.rapidapi.com/FindDrivingRoute?stops=$startPointLat,$startPointLng%3B$endPointLat,$endPointLng"
            val request = Request.Builder()
                .url(url)
                .get()
                .addHeader("X-RapidAPI-Key", "b75b1af2dbmshcf32852a614c58cp1280cajsne0e57708a417")
                .addHeader("X-RapidAPI-Host", "trueway-directions2.p.rapidapi.com")
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Toast.makeText(this@MapsActivity, "Getting error", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val jsonData = response.body?.string()
                        Log.d("-----------", "onResponse: ${jsonData}")

                        val directionsData = parseDirections(jsonData)
                        val points = directionsData.first
                        val distance = directionsData.second
                        val duration = directionsData.third

                        val distanceInMeters =distance .toDouble()

                        Log.d("-----------", "onResponse: ${points}, Distance: $distance")

                        val distanceInKm = distanceInMeters/1000.0
                        runOnUiThread {
                            drawPolyline(points)
                            if (distance.isNotEmpty()) {
                                // Update the TextView with the distance
                                val distanceTxt = findViewById<TextView>(R.id.totalDistance)
//                                val durationTxt = findViewById<TextView>(R.id.duration)
                                distanceTxt.text = "$distanceInKm km "
//                                durationTxt.text = "${duration}"
                                Toast.makeText(this@MapsActivity, "Distance: $distance", Toast.LENGTH_LONG).show()
                            }

                            if (distance.isNotEmpty()) {
                                Toast.makeText(this@MapsActivity, "Distance: $distance", Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                }
            })
            Log.d("--------", "requestDirections: $url")
        }
    }

    private fun parseDirections(jsonData: String?): Triple<List<LatLng>, String, String> {
        val points = ArrayList<LatLng>()
        var distance = ""
        val duration = ""

        try {
            if (jsonData.isNullOrBlank()) {
                Log.e("Parse Directions", "JSON data is null or blank")
                return Triple(points, distance, duration)
            }

            val jsonObject = JSONObject(jsonData)
            val route = jsonObject.optJSONObject("route")

            if (route == null) {
                Log.e("Parse Directions", "No route found in JSON data")
                return Triple(points, distance, duration)
            }

            val geometry = route.optJSONObject("geometry")

            if (geometry == null) {
                Log.e("Parse Directions", "No geometry found in JSON data")
                return Triple(points, distance, duration)
            }

            val coordinates = geometry.optJSONArray("coordinates")

            if (coordinates == null || coordinates.length() == 0) {
                Log.e("Parse Directions", "No coordinates found in JSON data")
                return Triple(points, distance, duration)
            }

            for (i in 0 until coordinates.length()) {
                val coordinate = coordinates.optJSONArray(i)

                if (coordinate != null && coordinate.length() == 2) {
                    val lat = coordinate.getDouble(0)
                    val lng = coordinate.getDouble(1)
                    points.add(LatLng(lat, lng))
                }
            }
            distance = route.optString("distance", "")

        } catch (e: JSONException) {
            e.printStackTrace()
            Log.e("Parse Directions", "Error parsing JSON data: ${e.message}")
        }

        return  return Triple(points, distance, duration)
    }

    fun getLatLngFromAddress(address: String): Pair<Double, Double>? {
        try {
            val addresses: List<Address> = geocoder.getFromLocationName(address, 1)!!
            if (addresses.isNotEmpty()) {
                val latitude = addresses[0].latitude
                val longitude = addresses[0].longitude
                return Pair(latitude, longitude)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    private fun drawPolyline(points: List<LatLng>?) {
        if (points != null && points.isNotEmpty()) {
            val polylineOptions = PolylineOptions()
                .addAll(points)
                .color(Color.BLUE)
                .width(7f)

            map.addPolyline(polylineOptions)

            val builder = LatLngBounds.Builder()
            for (point in points) {
                builder.include(point)
            }
            val bounds = builder.build()

            val originAddress = originEdt.text.toString()
            val destinationAddress = destinationEdt.text.toString()

            val originLatLng = getLatLngFromAddress(originAddress!!)
            val destinationLatLng = getLatLngFromAddress(destinationAddress!!)

            if (originLatLng != null && destinationLatLng != null) {
                addMarker(LatLng(originLatLng.first, originLatLng.second), originAddress, BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                addMarker(LatLng(destinationLatLng.first, destinationLatLng.second), destinationAddress, BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
            } else {
                Log.e("MapsActivity", "Error getting LatLng from address")
            }

            val padding = 50
            val cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, padding)

            map.animateCamera(cameraUpdate)

            try {
                MapsInitializer.initialize(this)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            Log.e("Draw Polyline", "No valid points to draw")
        }
    }

    private fun addMarker(latLng: LatLng, title: String?, icon: BitmapDescriptor) {
        map.addMarker(MarkerOptions().position(latLng).title(title).icon(icon))
    }

}

