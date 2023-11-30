package com.example.fm_hub_yeonsinkeem2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fm_hub_yeonsinkeem2.databinding.ItemRecyclerviewBinding

class MyViewHolder (val binding: ItemRecyclerviewBinding):
        RecyclerView.ViewHolder(binding.root)

class MyAdapter(val datas: MutableList<Movie>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int = datas.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder = MyViewHolder(ItemRecyclerviewBinding.inflate(
        LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyViewHolder).binding
        val person = datas[position]

        // binding.itemData.text = "${person.title}\n${movie.res_per}"
        binding.itemImage.setImageResource(person.imageResId)
        //binding.itemData.text = datas[position]
        //binding.itemImage.setImageResource(android.R.drawable.star_on)
    }
}
