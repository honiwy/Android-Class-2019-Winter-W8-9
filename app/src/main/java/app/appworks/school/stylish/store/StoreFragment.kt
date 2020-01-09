package app.appworks.school.stylish.store


import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import app.appworks.school.stylish.R
import app.appworks.school.stylish.collect.CollectViewModel
import app.appworks.school.stylish.databinding.FragmentMapBinding
import app.appworks.school.stylish.ext.getVmFactory
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class StoreFragment : Fragment(), OnMapReadyCallback {
    //widgets
//    private var mUserListRecyclerView: RecyclerView? = null
    val MAPVIEW_BUNDLE_KEY: String? = "MapViewBundleKey"
    private var mMapView: MapView? = null
    private lateinit var mMap: GoogleMap
    private val viewModel by viewModels<StoreViewModel> { getVmFactory() }
    @Nullable
    override fun onCreateView(@NonNull inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View {


        val binding = FragmentMapBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        mMapView = binding.mapView
        initGoogleMap(savedInstanceState)

        return binding.root
    }

    private fun initGoogleMap(savedInstanceState: Bundle?) { // *** IMPORTANT ***
// MapView requires that the Bundle you pass contain _ONLY_ MapView SDK
// objects or sub-Bundles.
        var mapViewBundle: Bundle? = null
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY)
        }
        mMapView!!.onCreate(mapViewBundle)
        mMapView!!.getMapAsync(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        var mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY)
        if (mapViewBundle == null) {
            mapViewBundle = Bundle()
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle)
        }
        mMapView!!.onSaveInstanceState(mapViewBundle)
    }

    override fun onResume() {
        super.onResume()
        mMapView!!.onResume()
    }

    override fun onStart() {
        super.onStart()
        mMapView!!.onStart()
    }

    override fun onStop() {
        super.onStop()
        mMapView!!.onStop()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        val sydney2 = LatLng(25.0424488,121.562731)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.addMarker(MarkerOptions().position(sydney2).title("We are hiring"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney2))
    }

    override fun onPause() {
        mMapView!!.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mMapView!!.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mMapView!!.onLowMemory()
    }

    companion object {
        private const val TAG = "UserListFragment"
        fun newInstance(): StoreFragment {
            return StoreFragment()
        }
    }

}
