package com.example.fm_hub;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {
    private ArrayList<FriendItem> mFriendList;
    @NonNull
    @Override
    public MyRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_recyclerview,parent,false);
        //return new ViewHolder(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerAdapter.ViewHolder holder, int position) {
        holder.onBind(mFriendList.get(position));
    }
    public void setFriendList(ArrayList<FriendItem> list) {
        this.mFriendList = list;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return mFriendList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profile;
        TextView name;
        TextView reserve;
        TextView audience;

        public ViewHolder(View itemView) {
            super(itemView);
            profile = (ImageView) itemView.findViewById(R.id.profile);
            name = (TextView)itemView.findViewById(R.id.name);
            reserve = (TextView) itemView.findViewById(R.id.reserve);
            audience = (TextView) itemView.findViewById(R.id.audience);
        }
        void onBind(FriendItem item) {
            profile.setImageResource(item.getResourceId());
            name.setText(item.getName());
            reserve.setText(item.getReserve());
            audience.setText(item.getAudience());
        }
    }
}