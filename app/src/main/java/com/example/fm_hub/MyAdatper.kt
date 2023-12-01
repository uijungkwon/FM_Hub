package com.example.fm_hub;

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fm_hub.databinding.MovieItemRecyclerviewBinding
import com.example.fm_hub.model.DetailInfo

class MyViewHolder(val binding: MovieItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root){
    init{
        binding.itemTitle.setOnClickListener {
            val intent = Intent(binding.root.context, InfoActivity::class.java)
            intent.putExtra("title", binding.itemTitle.text)
            startActivity(binding.root.context, intent, null)
        }
    }
}
class MyAdapter(val context: Context, val datas: MutableList<DetailInfo>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun getItemCount(): Int{
        return datas?.size ?: 0
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = MyViewHolder(MovieItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding=(holder as MyViewHolder).binding

        //add......................................
        val model = datas!![position]
        var splitUrl:String = model.posters.toString().split("|")[0]

            if(splitUrl.isEmpty()){
            }
            else{
                Glide.with(this.context)
                    .load(splitUrl)
                    .into(binding.itemImage)
            }
        binding.itemTitle.text = model.title
        binding.itemGenre.text = "장르: "+model.genre
        binding.itemReleaseDate.text = "개봉일: "+model.repRlsDate
    }
}

