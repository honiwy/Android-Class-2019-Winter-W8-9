package app.appworks.school.stylish.store


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import app.appworks.school.stylish.R
import app.appworks.school.stylish.StylishApplication
import app.appworks.school.stylish.databinding.FragmentMapBinding
import app.appworks.school.stylish.ext.getVmFactory
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class StoreFragment : Fragment(), OnMapReadyCallback {
    val MAPVIEW_BUNDLE_KEY: String? = "MapViewBundleKey"
    private var mMapView: MapView? = null
    private lateinit var mMap: GoogleMap
    private val viewModel by viewModels<StoreViewModel> { getVmFactory() }
    @Nullable
    override fun onCreateView(@NonNull inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View {


        val binding = FragmentMapBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.spinner.adapter = StoreSpinnerAdapter(
            StylishApplication.instance.resources.getStringArray(R.array.branch_store_list))



        viewModel.branchName.observe(this, Observer {
            it?.let {
                when(it){
                    Branch.TAIWAN ->{
                        binding.storeTitle.text="臺灣分店"
                        binding.storeTime.text=StylishApplication.instance.getString(R.string.time_prefix).plus("11:00-17:00")
                        binding.storeAddress.text=StylishApplication.instance.getString(R.string.address_prefix).plus(StylishApplication.instance.getString(R.string.taiwan_address))
                        binding.storePhone.text= StylishApplication.instance.getString(R.string.phone_prefix).plus(StylishApplication.instance.getString(R.string.taiwan_phone))
                        moveLocation(25.0424801,121.5627567,"You smile, I smile")
                    }
                    Branch.AUSTRALIA->{
                        binding.storeTitle.text="澳洲分店"
                        binding.storeTime.text = StylishApplication.instance.getString(R.string.time_prefix).plus("10:00-13:30")
                        binding.storeAddress.text=StylishApplication.instance.getString(R.string.address_prefix).plus(StylishApplication.instance.getString(R.string.australia_address))
                        moveLocation(-27.736946,153.225838,"Be part of Us")
                    }
                    Branch.MOROCCO->{
                        binding.storeTitle.text="摩洛哥分店"
                        binding.storeTime.text=StylishApplication.instance.getString(R.string.time_prefix).plus("09:00-14:00")
                        binding.storeAddress.text=StylishApplication.instance.getString(R.string.address_prefix).plus(StylishApplication.instance.getString(R.string.morocco_address))
                        binding.storePhone.text=StylishApplication.instance.getString(R.string.phone_prefix).plus(StylishApplication.instance.getString(R.string.morocco_phone))
                        moveLocation(33.955304,-6.8965387,"Everything is on sale!")
                    }
                    Branch.FINLAND->{
                        binding.storeTitle.text="芬蘭分店"
                        binding.storeTime.text=StylishApplication.instance.getString(R.string.time_prefix).plus("12:00-18:30")
                        binding.storeAddress.text=StylishApplication.instance.getString(R.string.address_prefix).plus(StylishApplication.instance.getString(R.string.finland_address))
                        binding.storePhone.text=StylishApplication.instance.getString(R.string.phone_prefix).plus(StylishApplication.instance.getString(R.string.finland_phone))
                        moveLocation(60.167014,24.9568524,"We are hiring!")
                    }
                }
            }
        })


        mMapView = binding.mapView
        initGoogleMap(savedInstanceState)

        return binding.root
    }

    fun moveLocation(positionX:Double,positionY:Double,info:String){
        val restoredCamera = CameraPosition.Builder()
            .target(
                LatLng(
                    positionX,positionY
                )
            )
            .zoom(15f)
            .bearing(0f) // Face north
            .tilt(0f) // reset tilt (directly facing the Earth)
            .build()
        mMap.addMarker(MarkerOptions().position(LatLng(positionX,positionY)).title(info))
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(restoredCamera))
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
       // val sydney = LatLng(-34.0, 151.0)
       // val zoom = CameraUpdateFactory.zoomTo(15f)

       // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
       // mMap.animateCamera(zoom)
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
