package com.example.fm_hub_uijung

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fm_hub_uijung.databinding.FragmentRetrofitBinding
import com.example.fm_hub_uijung.model.MovieDetailInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRetrofitBinding.inflate(inflater, container, false)

        val call: Call<MovieDetailInfo> = MyApplication.networkService3
            .getList(
                "Y",
                "202301",
            MyApplication.SERVICEKEY,
        )
        call?.enqueue(object: Callback<MovieDetailInfo> {
            override fun onResponse(call: Call<MovieDetailInfo>, response: Response<MovieDetailInfo>) {
                if(response.isSuccessful){
                    binding.retrofitRecyclerView.layoutManager = LinearLayoutManager(activity)
                    binding.retrofitRecyclerView.adapter = MyAdapter(activity as Context, response.body()?.Data?.get(0)?.Result)
                }
            }
            override fun onFailure(call: Call<MovieDetailInfo>, t: Throwable) {
                Log.d("kwonkwon", "error.......")
            }
        })
        return binding.root
    }


}