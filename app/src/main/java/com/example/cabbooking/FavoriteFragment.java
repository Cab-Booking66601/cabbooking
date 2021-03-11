package com.example.cabbooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {

    ArrayList<cabItem> houseFavItem;
    RecyclerView houseHuntFavRecyclerView;
    cabAdapterFav houseHuntFavAdapter;
    RecyclerView.LayoutManager houseHuntFavLayoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fav_fragement, container, false);

        houseFavItem = ((GlobalArraylist) this.getActivity().getApplication()).getGlobalArraylist();

        if (houseFavItem != null) {
            houseHuntFavRecyclerView = v.findViewById(R.id.recyclerViewFav);
            houseHuntFavLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), RecyclerView.VERTICAL, false);
            houseHuntFavAdapter = new cabAdapterFav(houseFavItem);

            houseHuntFavRecyclerView.setLayoutManager(houseHuntFavLayoutManager);
            houseHuntFavRecyclerView.setAdapter(houseHuntFavAdapter);

            houseHuntFavAdapter.setOnItmeClickListner(position -> {
                Intent intent = new Intent(getActivity().getApplicationContext(), cabDetail.class);

                intent.putExtra("Name", houseFavItem.get(position).getName());
                intent.putExtra("Area", houseFavItem.get(position).getArea());
                intent.putExtra("Price", houseFavItem.get(position).getPrice());
                intent.putExtra("Rating", houseFavItem.get(position).getRating());
                intent.putExtra("PhoneNumber", houseFavItem.get(position).getPhoneNumber());
                intent.putExtra("ImageUrl", houseFavItem.get(position).getImageUrl());

                intent.putExtra("Des", houseFavItem.get(position).getdes());

                startActivity(intent);
            });
        }

        return v;
    }
}