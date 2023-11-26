package com.example.fm_hub_uijung

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fm_hub_uijung.databinding.FragmentRetrofitBinding
import com.example.fm_hub_uijung.model.MovieListModel
import com.example.fm_hub_uijung.model.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRetrofitBinding.inflate(inflater, container, false)
/*
        val call: Call<MovieListModel> = MyApplication.networkService.getList(
            MyApplication.QUERY,
            MyApplication.API_KEY,
            1,
            20
        )*/
        val call: Call<MovieResponse> = MyApplication.networkService
            .getList(
            MyApplication.KEY,
                "2020",
                "2023",
                "7",
                "20",
        )
        call?.enqueue(object: Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if(response.isSuccessful){
                    binding.retrofitRecyclerView.layoutManager = LinearLayoutManager(activity)
                    binding.retrofitRecyclerView.adapter = MyAdapter(activity as Context, response.body()?.movieListResult?.movieList)
                }
            }
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d("kwonkwon", "error.......")
            }
        })
        return binding.root
    }


}