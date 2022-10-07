package com.example.kbway.userMap

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.kbway.R
import com.example.kbway.databinding.UserMapBinding
import com.example.kbway.userRoute.model.AllRouteData
import com.example.kbway.userSettings.SettingsFragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import timber.log.Timber


class UserMapFragment : Fragment(), LocationListener {

    private lateinit var client: FusedLocationProviderClient
    private lateinit var binding: UserMapBinding

    //inflating Fragment with MapLayout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //binding view Items
        binding = UserMapBinding.inflate(inflater, container, false)

        //showing map in the Fragment
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.userMapFragment) as SupportMapFragment?
        mapFragment!!.getMapAsync { mMap ->
            mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            //clear old markers
            mMap.clear()
            //creating map client
            client = LocationServices.getFusedLocationProviderClient(activity as AppCompatActivity)

            getCurrentLocation()

            val data2 =
                arguments?.getParcelable<AllRouteData.AllRouteDataItem?>("name")

            if (data2 != null) {
                mMap.addMarker(
                    MarkerOptions()
                        .position(LatLng((data2.routeDriver?.get(0)?.latRouteDrivers as Double),
                            (data2.routeDriver[0].lngRouteDrivers  as Double)))
                        .title("Автобус в пути!")
                        .icon(bitmapDescriptorFromVector(R.drawable.ic_bus_lacation))
                )
            }

            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(42.81346573481484, 73.84525104333657))
                    .title("IT Academy(IT Академия)")
                    .snippet("Здесь рождаются лучшие программисты Кыргызстана!")
                    .icon(bitmapDescriptorFromVector(R.drawable.ic_it_academy))
            )
            Handler(Looper.getMainLooper()).postDelayed({
                mMap.setOnCameraIdleListener {
                    binding.gpsButton.setImageResource(R.drawable.ic_gps_disabled)
                }
            }, 10000)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data =
            arguments?.getParcelable<AllRouteData.AllRouteDataItem?>("name")
        if (data != null) {

            when (data.routeNumber) {
                2 -> {
                    setRouteColor(Color.RED, data.routeCoordinates)

                }
                3 -> {
                    setRouteColor(Color.BLUE, data.routeCoordinates)

                }
                4 -> {
                    setRouteColor(Color.CYAN, data.routeCoordinates)

                }
                5 -> {
                    setRouteColor(Color.YELLOW, data.routeCoordinates)

                }
                6 -> {
                    setRouteColor(Color.MAGENTA, data.routeCoordinates)

                }
                7 -> {
                    setRouteColor(Color.GREEN, data.routeCoordinates)

                }
                8 -> {
                    setRouteColor(Color.RED, data.routeCoordinates)
                }
                9 -> {
                    setRouteColor(Color.CYAN, data.routeCoordinates)
                }
            }

            context?.let {
                try {
                    binding.routTextView.text = "Маршрут - ${data.routeNumber}"
                } catch (e: Exception) {
                    Timber.e("Bad data input $e")
                }
            }
        }
        binding.bottomButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.goBackImageView.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.menuImageView.setOnClickListener {
            changeFragment(SettingsFragment(), R.id.contentContainer)
        }
    }

    private fun setRouteColor(color: Int, double: List<List<Double>>?) {
        double?.let { getRouteLatLng(it) }?.let {
            PolylineOptions()
                .addAll(it)
                .width(25f)
                .color(color)
                .geodesic(true)
        }?.let {
            val mapFragment1 =
                childFragmentManager.findFragmentById(R.id.userMapFragment) as SupportMapFragment?
            mapFragment1!!.getMapAsync { googleMap ->
                googleMap.addPolyline(
                    it
                )
            }
        }
    }

    private fun getRouteLatLng(listOfListOfDouble: List<List<Double>>): List<LatLng> {
        val listOfLatLng = ArrayList<LatLng>(listOfListOfDouble.size)
        for (latLng in listOfListOfDouble) {
            listOfLatLng.add(LatLng(latLng.first(), latLng.last()))
        }
        return listOfLatLng
    }

    private fun getCurrentLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
//                get your latitude and longitude in hear
                if (ActivityCompat.checkSelfPermission(
                        (context as AppCompatActivity),
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        (context as AppCompatActivity),
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    requestPermission()
                    return
                }

                client.lastLocation.addOnCompleteListener((context as AppCompatActivity)) { task ->
                    val location: Location? = task.result
                    Toast.makeText(
                        context as AppCompatActivity,
                        "${location?.longitude}",
                        Toast.LENGTH_SHORT
                    ).show()
                    if (location == null) {
                        Toast.makeText(
                            (context as AppCompatActivity),
                            "There is null data",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    } else {
                        val mapFragment =
                            childFragmentManager.findFragmentById(R.id.userMapFragment) as SupportMapFragment?
                        mapFragment!!.getMapAsync { mMap ->

                            binding.gpsButton.setOnClickListener {
                                binding.gpsButton.setImageResource(R.drawable.ic_gps_enabled)
                                val zoomedFragment =
                                    childFragmentManager.findFragmentById(R.id.userMapFragment) as SupportMapFragment?
                                zoomedFragment!!.getMapAsync { zoomedMap ->
                                    zoomedMap.mapType = GoogleMap.MAP_TYPE_NORMAL
                                    val zoomedCamera = CameraPosition.builder()
                                        .target(
                                            LatLng(
                                                location.latitude,
                                                location.longitude
                                            )/*LatLng(zoomCurrentLocation.latitude, zoomCurrentLocation.longitude)*/
                                        )
                                        .zoom(18f)
                                        .bearing(0f)
                                        .tilt(45f)
                                        .build()
                                    zoomedMap.animateCamera(
                                        CameraUpdateFactory.newCameraPosition(zoomedCamera),
                                        1000,
                                        null
                                    )
                                }
                            }

                            mMap.addMarker(
                                MarkerOptions()
                                    .position(
                                        LatLng(
                                            location.latitude,
                                            location.longitude
                                        )
                                    )
                                    .title("Вы находитесь здесь!")
                                        //данные приходят когда захотят, но приходят
                                    .icon(bitmapDescriptorFromVector(R.drawable.ic_user_location))
                            )
                            val startCamera =
                                LatLng(
                                    location.latitude,
                                    location.longitude
                                )
                                    ?.let {
                                        CameraPosition.builder()
                                            .target(it /*LatLng(firstCurrLocation.latitude, firstCurrLocation.longitude)*/)
                                            .zoom(16f)
                                            .bearing(0f)
                                            .tilt(45f)
                                            .build()
                                    }
                            startCamera?.let { CameraUpdateFactory.newCameraPosition(it) }
                                ?.let { mMap.animateCamera(it, 1000, null) }
                        }
                    }
                }
            } else {
                //settings open hear
                Toast.makeText(
                    (context as AppCompatActivity),
                    "Torn on location",
                    Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            //request permission hear
            requestPermission()
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            (requireActivity()),
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            PERMISSION_REQUEST_ACCESS_LOCATION
        )
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                (context as AppCompatActivity),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                (context as AppCompatActivity),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    companion object {
        private const val PERMISSION_REQUEST_ACCESS_LOCATION = 100
    }


    private fun bitmapDescriptorFromVector(vectorResId: Int): BitmapDescriptor {
        val vectorDrawable = ContextCompat.getDrawable(requireContext(), vectorResId)
        vectorDrawable!!.setBounds(
            0,
            0,
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight
        )
        val bitmap =
            Bitmap.createBitmap(
                vectorDrawable.intrinsicWidth,
                vectorDrawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
        val canvas = Canvas(bitmap)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    private fun changeFragment(fragment: Fragment, id: Int) {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction1: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction1.addToBackStack(null)
            .replace(id, fragment)
            .commit()
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_ACCESS_LOCATION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(requireActivity().applicationContext, "Granted", Toast.LENGTH_SHORT)
                    .show()
                getCurrentLocation()
            } else {
                Toast.makeText(requireActivity().applicationContext, "Denied", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    //это доп колбэк на случай если соновной не сработает
    override fun onLocationChanged(location: Location) {
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.userMapFragment) as SupportMapFragment?
        mapFragment!!.getMapAsync { mMap ->

            binding.gpsButton.setOnClickListener {
                binding.gpsButton.setImageResource(R.drawable.ic_gps_enabled)

                mMap.addMarker(
                    MarkerOptions()
                        .position(
                            LatLng(
                                location.latitude,
                                location.longitude
                            )
                        )
                        .title("Вы находитесь здесь!")
                        .icon(bitmapDescriptorFromVector(R.drawable.ic_user_location))
                )
            }
        }
    }
}