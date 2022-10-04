package com.example.hunger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;

    Animation logoAnim, textAnim;
    ImageView logo;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        logo = findViewById(R.id.logo);
        text = findViewById(R.id.text);

        //Animations
        logoAnim = AnimationUtils.loadAnimation(this, R.anim.logo_animation);
        textAnim = AnimationUtils.loadAnimation(this, R.anim.text_anim);

        //Set animation to elements
        logo.setAnimation(logoAnim);
        text.setAnimation(textAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);
    }
}