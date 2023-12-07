package com.example.fm_hub

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.fm_hub.databinding.ActivityCinemaLocationViewBinding
import com.example.fm_hub.model.GeoLocation
import com.example.fm_hub.model.Quad
import com.google.android.gms.location.LocationRequest
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.util.MarkerIcons


class CinemaLocationView : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var locationSource: FusedLocationSource
    private lateinit var naverMap: NaverMap
    lateinit var geoLocationList: ArrayList<Quad<GeoLocation, String, String, String>>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cinema_location_view)

        val binding = ActivityCinemaLocationViewBinding.inflate(layoutInflater)
        val locationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        val fm = supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map, it).commit()
            }




        mapFragment.getMapAsync(this)
        //뷰모델
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)

        //비동기 처리
        geoLocationList = CinemaDataStorage.geoLocationList as ArrayList<Quad<GeoLocation, String, String, String>>

    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        if (locationSource.onRequestPermissionsResult(requestCode, permissions,
                grantResults)) {
            if (!locationSource.isActivated) {
                naverMap.locationTrackingMode = LocationTrackingMode.None
            }
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }



    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap
        naverMap.locationSource = locationSource
        val uiSettings = naverMap.uiSettings
        var currentInfoWindow: InfoWindow? = null
        uiSettings.isLocationButtonEnabled = true
        uiSettings.isCompassEnabled = true

        //마커 서울시청 임시
        val marker = Marker()
        marker.position = LatLng(37.5670135, 126.9783740)
        marker.map = naverMap


        //로그
        Log.d("CinemaLocationView", "geoLocationList: $geoLocationList")
        //다중 마커
        for (i in 0 until geoLocationList.size) {
            val marker = Marker()
            marker.position = LatLng(geoLocationList[i].first.lng, geoLocationList[i].first.lat)
            marker.icon = MarkerIcons.BLACK
            marker.map = naverMap
            marker.tag =  geoLocationList[i].third + "\n주소 : "+ geoLocationList[i].second  + "\ntel : " +  (geoLocationList[i].fourth.replace("null", "정보 없음"))

            // 마커 클릭 시 정보창 & 다른 것 클릭 시 닫기
            marker.setOnClickListener {
                // 현재 열려 있는 InfoWindow가 있다면 닫기
                currentInfoWindow?.close()

                val infoWindow = InfoWindow()
                infoWindow.adapter = object : InfoWindow.DefaultTextAdapter(this) {
                    override fun getText(infoWindow: InfoWindow): CharSequence {
                        return marker.tag as CharSequence
                    }
                }
                infoWindow.open(marker)
                // 열린 InfoWindow를 현재 열린 InfoWindow로 설정
                currentInfoWindow = infoWindow
                true
            }
        }
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }
    //back 기능 정의
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}



