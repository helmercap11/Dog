package com.helmercapassola.dog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.ViewHolder> {


    private List<Dog> dogList;
    private Context context;

    public DogAdapter(List<Dog> dogList, Context context) {
        this.dogList = dogList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.content_main, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Dog dog = dogList.get(position);

        holder.txt_title.setText(dog.getTitle());
        holder.txt_desc.setText(dog.getDesc());
        holder.imageView.setImageResource(dog.getImage());
        }

    @Override
    public int getItemCount() {
        return dogList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txt_title;
        TextView txt_desc;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_title = itemView.findViewById(R.id.txt_title);
            txt_desc = itemView.findViewById(R.id.txt_desc);
            imageView = itemView.findViewById(R.id.imageview);
        }
    }
}
