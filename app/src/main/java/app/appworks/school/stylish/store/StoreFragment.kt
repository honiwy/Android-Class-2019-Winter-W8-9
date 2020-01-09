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
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import app.appworks.school.stylish.NavigationDirections
import app.appworks.school.stylish.R
import app.appworks.school.stylish.StylishApplication
import app.appworks.school.stylish.collect.CollectViewModel
import app.appworks.school.stylish.databinding.FragmentMapBinding
import app.appworks.school.stylish.dialog.MessageDialog
import app.appworks.school.stylish.ext.getVmFactory
import app.appworks.school.stylish.payment.PaymentSpinnerAdapter
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
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
                    }
                    Branch.AUSTRALIA->{
                        binding.storeTitle.text="澳洲分店"
                        binding.storeTime.text = StylishApplication.instance.getString(R.string.time_prefix).plus("10:00-13:30")
                        binding.storeAddress.text=StylishApplication.instance.getString(R.string.address_prefix).plus(StylishApplication.instance.getString(R.string.australia_address))
                    }
                    Branch.MOROCCO->{
                        binding.storeTitle.text="摩洛哥分店"
                        binding.storeTime.text=StylishApplication.instance.getString(R.string.time_prefix).plus("09:00-14:00")
                        binding.storeAddress.text=StylishApplication.instance.getString(R.string.address_prefix).plus(StylishApplication.instance.getString(R.string.morocco_address))
                        binding.storePhone.text=StylishApplication.instance.getString(R.string.phone_prefix).plus(StylishApplication.instance.getString(R.string.morocco_phone))
                    }
                    Branch.FINLAND->{
                        binding.storeTitle.text="芬蘭分店"
                        binding.storeTime.text=StylishApplication.instance.getString(R.string.time_prefix).plus("12:00-18:30")
                        binding.storeAddress.text=StylishApplication.instance.getString(R.string.address_prefix).plus(StylishApplication.instance.getString(R.string.finland_address))
                        binding.storePhone.text=StylishApplication.instance.getString(R.string.phone_prefix).plus(StylishApplication.instance.getString(R.string.finland_phone))
                    }
                }
            }
        })


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
        val zoom = CameraUpdateFactory.zoomTo(15f)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.addMarker(MarkerOptions().position(sydney2).title("We are hiring"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney2))
        mMap.animateCamera(zoom)
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
