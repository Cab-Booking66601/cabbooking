package com.example.cabbooking;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class cabAdapter extends RecyclerView.Adapter<cabAdapter.houseViewHolder> {

    ArrayList<cabItem> cabItems = new ArrayList<>();

    cabAdapter(ArrayList<cabItem> houseItems) {
        this.cabItems = houseItems;
    }

    private OnItemClickListner mListner;

    public interface OnItemClickListner {
        void onItemClick(int position);
    }

    public void setOnItmeClickListner(OnItemClickListner listner) {
        mListner = listner;
    }


    @NonNull
    @Override
    public houseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cab_item, parent, false);

        houseViewHolder hvh = new houseViewHolder(v, mListner);
        return hvh;
    }

    @Override
    public void onBindViewHolder(@NonNull houseViewHolder holder, int position) {
        cabItem currentItem = cabItems.get(position);

        Picasso.with(currentItem.getContext()).load(currentItem.getImageUrl()).resize(690, 450).into(holder.image);

        holder.name.setText(currentItem.getName());
        holder.area.setText(currentItem.getArea());
        holder.price.setText(currentItem.getPrice());
        holder.rating.setText(currentItem.getRating());
        holder.phoneNumber.setText(currentItem.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return cabItems.size();
    }

    public class houseViewHolder extends RecyclerView.ViewHolder {
        public TextView name, area, price, rating, phoneNumber;
        ImageView image;

        public houseViewHolder(@NonNull View itemView, final OnItemClickListner listner) {
            super(itemView);
            image = itemView.findViewById(R.id.imageView);

            name = itemView.findViewById(R.id.text_name);
            area = itemView.findViewById(R.id.text_area);
            price = itemView.findViewById(R.id.text_price);
            rating = itemView.findViewById(R.id.text_rating);
            phoneNumber = itemView.findViewById(R.id.phoneNumber);

            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                listner.onItemClick(position);
            });
        }
    }
}
