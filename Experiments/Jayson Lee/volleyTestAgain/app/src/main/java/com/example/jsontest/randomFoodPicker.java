package com.example.jsontest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class randomFoodPicker extends AppCompatActivity implements Animation.AnimationListener {

    boolean blnButtonRotation = true;
    int intNumber = 6;
    long lngDegrees = 0;
    SharedPreferences sharedPreferences;
    ImageView selected, imageRoulette;
    private PreferenceManager PreferenceManaager;

    Button spinBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(1024);
        requestWindowFeature(1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_food_picker);

        spinBtn = (Button) findViewById(R.id.spinButton);
        selected = (ImageView) findViewById(R.id.pointer);
        imageRoulette = (ImageView) findViewById(R.id.fortuneWheel);
        this.sharedPreferences = PreferenceManaager.getDefaultSharedPreferences(this);
        this.intNumber = this.sharedPreferences.getInt("INT_NUMBER", 6);

    }

    @Override
    public void onAnimationStart(Animation animation) {
        this.blnButtonRotation = false;
        spinBtn.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Toast toast = Toast.makeText(this, " " + String.valueOf((int) (((double)this.intNumber)
                - Math.floor(((double) this.lngDegrees)/ (360.0d / ((double)this.intNumber)))))+ " ",0);
        toast.setGravity(49,0,0);
        toast.show();
        this.blnButtonRotation = true;
        spinBtn.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    public void onClickButtonRotation (View v){

        if(this.blnButtonRotation){
            int ran = new Random().nextInt(360) + 3600;
            RotateAnimation rotateAnimation = new RotateAnimation((float)this.lngDegrees, (float)
                    (this.lngDegrees + ((long) ran)),1,0.5f,1,0.5f);
            this.lngDegrees = (this.lngDegrees + ((long)ran)) % 360;
            rotateAnimation.setDuration((long) ran);
            rotateAnimation.setFillAfter(true);
            rotateAnimation.setInterpolator(new DecelerateInterpolator());
            rotateAnimation.setAnimationListener(this);
            imageRoulette.setAnimation(rotateAnimation);
            imageRoulette.startAnimation(rotateAnimation);

        }
    }
}