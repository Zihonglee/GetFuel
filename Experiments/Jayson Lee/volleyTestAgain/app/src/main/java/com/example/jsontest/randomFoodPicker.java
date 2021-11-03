package com.example.jsontest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bluehomestudio.luckywheel.LuckyWheel;
import com.bluehomestudio.luckywheel.OnLuckyWheelReachTheTarget;
import com.bluehomestudio.luckywheel.WheelItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class randomFoodPicker extends AppCompatActivity {

    private LuckyWheel lw;
    List<WheelItem> wheelItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_food_picker);

        generateWheelItems();

        lw = findViewById(R.id.lwv);
        lw.addWheelItems(wheelItems);
        lw.setTarget(1);
        Random rand = new Random();

        lw.setLuckyWheelReachTheTarget(new OnLuckyWheelReachTheTarget() {
            @Override
            public void onReachTarget() {
                Toast.makeText(randomFoodPicker.this, "Your restaurant", Toast.LENGTH_SHORT).show();
            }
        });

        Button spinBtn = findViewById(R.id.spinButton);
        spinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lw.rotateWheelTo(rand.nextInt(5));
            }
        });
    }

    private void generateWheelItems(){

        wheelItems = new ArrayList<>();
        wheelItems.add(new WheelItem(Color.parseColor("#fc6c6c"), BitmapFactory.decodeResource(getResources(),R.drawable.ic_action_name),
                "food"));
        wheelItems.add(new WheelItem(Color.parseColor("red"), BitmapFactory.decodeResource(getResources(),R.drawable.ic_action_name),
                "money"));
        wheelItems.add(new WheelItem(Color.parseColor("black"), BitmapFactory.decodeResource(getResources(),R.drawable.ic_action_name),
                "tasty"));
        wheelItems.add(new WheelItem(Color.parseColor("gray"), BitmapFactory.decodeResource(getResources(),R.drawable.ic_action_name),
                "redbull"));
        wheelItems.add(new WheelItem(Color.parseColor("green"), BitmapFactory.decodeResource(getResources(),R.drawable.ic_action_name),
                "drinks"));
        wheelItems.add(new WheelItem(Color.parseColor("blue"), BitmapFactory.decodeResource(getResources(),R.drawable.ic_action_name),
                "smiles"));
    }

}