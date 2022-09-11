package com.example.kbway.userMap

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.kbway.R
import com.example.kbway.databinding.UserMapBinding
import com.example.kbway.userRoute.model.AllRouteData
import com.example.kbway.userSettings.SettingsFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import timber.log.Timber

class UserMapFragment : Fragment() {

    private lateinit var binding: UserMapBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = UserMapBinding.inflate(inflater, container, false)
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.userMapFragment) as SupportMapFragment?
        mapFragment!!.getMapAsync { mMap ->
            mMap.mapType = GoogleMap.MAP_TYPE_NORMAL

            mMap.clear() //clear old markers

            val googlePlex = CameraPosition.builder()
                .target(LatLng(42.808638, 73.847880))
                .zoom(16f)
                .bearing(0f)
                .tilt(45f)
                .build()

            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 3000, null)

            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(42.808638, 73.847880))
                    .title("Вы находитесь здесь!")
                    .icon(bitmapDescriptorFromVector(activity, R.drawable.ic_user_location))
            )

            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(42.797655661005464, 73.85601008276385))
                    .title("Автобус в пути!")
                    .icon(bitmapDescriptorFromVector(activity, R.drawable.ic_bus_lacation))
            )

            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(42.81346573481484, 73.84525104333657))
                    .title("IT Academy(IT Академия)")
                    .snippet("Здесь рождаются лучшие программисты Кыргызстана!")
                    .icon(bitmapDescriptorFromVector(activity, R.drawable.ic_it_academy))
            )
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data =
            arguments?.getParcelable<AllRouteData.AllRouteDataItem?>("name")
        if (data != null) {
            context?.let {
                try {
                    binding.routTextView.text = "Маршрут - ${data.routeNumber}"
                } catch (e: Exception) {
                    Timber.e("Bad data input $e")
                }
            }
        }

        binding.menuImageView.setOnClickListener {
            changeFragment(SettingsFragment(), R.id.contentContainer)
        }
    }

    private fun bitmapDescriptorFromVector(context: Context?, vectorResId: Int): BitmapDescriptor {
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
}