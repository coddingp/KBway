package com.example.kbway.userMap

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.google.android.gms.tasks.Task
import timber.log.Timber

class UserMapFragment : Fragment() {

    private lateinit var client: FusedLocationProviderClient
    private lateinit var binding: UserMapBinding
//    private lateinit var myLocation: LatLng

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

            val firstCurrLocation = getCurrentLocation()

            val startCamera = CameraPosition.builder()
                .target(LatLng(firstCurrLocation.latitude, firstCurrLocation.longitude))
                .zoom(16f)
                .bearing(0f)
                .tilt(45f)
                .build()

            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(startCamera), 2000, null)

            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(firstCurrLocation.latitude, firstCurrLocation.longitude))
                    .title("Вы находитесь здесь!")
                    .icon(bitmapDescriptorFromVector(R.drawable.ic_user_location))
            )

            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(42.797655661005464, 73.85601008276385))
                    .title("Автобус в пути!")
                    .icon(bitmapDescriptorFromVector(R.drawable.ic_bus_lacation))
            )

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

        binding.gpsButton.setOnClickListener {
            binding.gpsButton.setImageResource(R.drawable.ic_gps_enabled)
            val zoomedFragment =
                childFragmentManager.findFragmentById(R.id.userMapFragment) as SupportMapFragment?
            zoomedFragment!!.getMapAsync { zoomedMap ->
                zoomedMap.mapType = GoogleMap.MAP_TYPE_NORMAL
                val zoomCurrentLocation = getCurrentLocation()
                val zoomedCamera = CameraPosition.builder()
                    .target(LatLng(zoomCurrentLocation.latitude, zoomCurrentLocation.longitude))
                    .zoom(18f)
                    .bearing(0f)
                    .tilt(45f)
                    .build()
                zoomedMap.animateCamera(
                    CameraUpdateFactory.newCameraPosition(zoomedCamera),
                    2000,
                    null
                )
            }
        }

        binding.menuImageView.setOnClickListener {
            changeFragment(SettingsFragment(), R.id.contentContainer)
        }
    }

    private fun setRouteColor(color: Int, double: List<List<Double>>?) {
        double?.let { getLatLng(it) }?.let {
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

    private fun getLatLng(listOfListOfDouble: List<List<Double>>): List<LatLng> {
        val listOfLatLng = ArrayList<LatLng>(listOfListOfDouble.size)
        for (latLng in listOfListOfDouble) {
            listOfLatLng.add(LatLng(latLng.first(), latLng.last()))
        }
        return listOfLatLng
    }

    private fun getCurrentLocation(): LatLng {
        var myLocation = LatLng(42.797655661005464, 78.85801008276300)
        //Checking permission
        if (ActivityCompat.checkSelfPermission(
                activity as AppCompatActivity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val locationTask: Task<Location> = client.lastLocation
            locationTask.addOnSuccessListener() { lastLocation ->
                // on Success
                if (lastLocation != null) {
                    //Sync map
                    SupportMapFragment().getMapAsync {
                        it.isMyLocationEnabled = true
                        val latLng = LatLng(lastLocation.latitude, lastLocation.longitude)
                        myLocation = latLng
                    }
                }
            }
        } else {
            ActivityCompat.requestPermissions(
                activity as AppCompatActivity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                44
            )

            val locationTask2: Task<Location> = client.lastLocation
            locationTask2.addOnSuccessListener { lastLocation ->
                // on Success
                if (lastLocation != null) {
                    //Sync map
                    SupportMapFragment().getMapAsync {
                        it.isMyLocationEnabled = true
                        val latLng2 = LatLng(lastLocation.latitude, lastLocation.longitude)
                        myLocation = latLng2
                    }
                }
            }
        }
        return myLocation
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
        if (requestCode == 44) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation()
            }
        }
    }
}