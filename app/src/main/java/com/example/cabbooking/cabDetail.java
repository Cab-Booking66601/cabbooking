package com.example.cabbooking;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.like.LikeButton;
import com.like.OnLikeListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class cabDetail extends AppCompatActivity {

    TextView name, area, price, rating, des;
    Button phoneNumber;
    ImageView image;

    ArrayList<cabItem> favHouseArrayList;

    cabItem h1;
    int index;

    LikeButton likeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cab_detail);

        likeButton = findViewById(R.id.likeBtn);

        image = findViewById(R.id.imageView);

        name = findViewById(R.id.text_name);
        area = findViewById(R.id.text_area);
        price = findViewById(R.id.text_price);
        rating = findViewById(R.id.text_rating);
        des = findViewById(R.id.text_des);
        phoneNumber = findViewById(R.id.phoneNumber);

        name.setText(getIntent().getStringExtra("Name"));
        area.setText(getIntent().getStringExtra("Area"));
        price.setText(getIntent().getStringExtra("Price"));
        rating.setText(getIntent().getStringExtra("Rating"));
        des.setText(getIntent().getStringExtra("Des"));

        favHouseArrayList = ((GlobalArraylist) this.getApplication()).getGlobalArraylist();

        h1 = new cabItem(getIntent().getStringExtra("ImageUrl"), getIntent().getStringExtra("Name"), getIntent().getStringExtra("area"), getIntent().getStringExtra("Price"), getIntent().getStringExtra("Rating"), getIntent().getStringExtra("PhoneNumber"), getIntent().getStringExtra("Des"));
        int i;

        if (favHouseArrayList == null) {
            favHouseArrayList = new ArrayList<>();
        } else {
            for (i = 0; i < favHouseArrayList.size(); i++) {
                if (favHouseArrayList.get(i).getName().toString().equals(name.getText().toString())) {
                    likeButton.setLiked(true);
                    index = i;
                    break;
                }
            }
        }

        Picasso.with(this).load(getIntent().getStringExtra("ImageUrl")).resize(400, 300).into(image);

        phoneNumber.setOnClickListener(view -> startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", getIntent().getStringExtra("PhoneNumber"), null))));

        likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                afterLike();
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                afterDislike();
            }
        });
    }

    private void afterDislike() {
        favHouseArrayList.remove(index);
        ((GlobalArraylist) this.getApplication()).setGlobalArraylist(favHouseArrayList);
        Toast.makeText(this, "Cab removed from favorite", Toast.LENGTH_SHORT).show();
    }

    private void afterLike() {
        index = favHouseArrayList.size();
        favHouseArrayList.add(h1);
        ((GlobalArraylist) this.getApplication()).setGlobalArraylist(favHouseArrayList);
        Toast.makeText(this, "Cab added to favorite", Toast.LENGTH_SHORT).show();
    }
}
