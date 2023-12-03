package com.example.fm_hub

import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class CinemaLocationHelper(private val context: Context) {
    private fun loadJSONFromAsset(fileName: String): String? {
        return try {
            val inputStream = context.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    //json 데이터 파싱 후 리스트 반환
    private fun jsonParsing(json: String): MutableList<AdressData> {
        val cinemaDataList = mutableListOf<AdressData>()

        try {
            val jsonObject = JSONObject(json)
            val jsonArray = jsonObject.getJSONArray("DATA")
            for (i in 0 until jsonArray.length()) {
                val item = jsonArray.getJSONObject(i)
                val bplcnm = item.getString("bplcnm")
                val sitewhladdr = item.getString("sitewhladdr")
                val sitetel = item.optString("sitetel", "N/A") // 예외 처리를 통한 기본값 설정


                val cinemaData = AdressData(sitewhladdr)
                cinemaDataList.add(cinemaData)

            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return cinemaDataList
    }
    private suspend fun getAddressCoordinatesAsync(address: String): GeoLocation? = withContext(
        Dispatchers.IO) {
        // 네트워크 호출 코드
        val naverClientId = "u7n2x9ryps"
        val naverClientSecret = "ALGxJE059cJWsktNvhbeBB1vjU3OFElHJe7RqB8E"
        val naverGeoCodingApiUrl = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode"

        // OkHttpClient 생성
        val client = OkHttpClient()

        // 네이버 지오코딩 API 호출을 위한 Request 생성
        val request = Request.Builder()
            .url("$naverGeoCodingApiUrl?query=$address")
            .header("X-NCP-APIGW-API-KEY-ID", naverClientId)
            .header("X-NCP-APIGW-API-KEY", naverClientSecret)
            .build()

        // API 호출 및 응답 처리
        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                Log.e("GeocodingError", "Error: ${response.code}")
                return@withContext null
            }

            val responseBody = response.body?.string()
            val jsonObject = JSONObject(responseBody)

            // JSON 파싱
            val addresses = jsonObject.getJSONArray("addresses")
            if (addresses.length() > 0) {
                val firstAddress = addresses.getJSONObject(0)
                val latitude = firstAddress.getDouble("x")
                val longitude = firstAddress.getDouble("y")
                return@withContext GeoLocation(latitude, longitude)
            }
        }

        return@withContext null
    }
    suspend fun getMarker(fileName: String): MutableList<GeoLocation> {
        val json = loadJSONFromAsset(fileName)
        return if (json != null) {
            val cinemaDataList = jsonParsing(json)
            val geoLocationList = mutableListOf<GeoLocation>()
            for (cinemaData in cinemaDataList) {
                val geoLocation = getAddressCoordinatesAsync(cinemaData.address)
                if (geoLocation != null) {
                    geoLocationList.add(geoLocation)
                }
            }
            geoLocationList
        } else {
            mutableListOf()
        }
    }
}