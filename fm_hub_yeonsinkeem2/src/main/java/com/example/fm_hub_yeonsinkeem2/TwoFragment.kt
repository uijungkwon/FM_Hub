package com.example.fm_hub_yeonsinkeem2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fm_hub_yeonsinkeem2.databinding.FragmentTwoBinding

data class Movie(val name: String, val imageResId: Int, val res_per: Double)
class TwoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTwoBinding.inflate(inflater, container, false)
        val datas = mutableListOf(
            Movie("하이큐!! 승자와 패자", R.drawable.movie1, 83.2),
            Movie("Call my by your name", R.drawable.movie2, 51.7),
            Movie("그대들은 어떻게 살 것인가", R.drawable.movie3, 46.1)
        )
        binding.recyclerview2.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerview2.adapter = MyAdapter(datas)
        binding.recyclerview2.addItemDecoration(DividerItemDecoration(activity, 1))

        return binding.root
    }
}