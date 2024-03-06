package bao.huynh.food_app_arnc.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import bao.huynh.food_app_arnc.R;

public class Splash_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }
    public void login(View view) {
        Intent intent = new Intent(Splash_Screen.this, Login.class);
        startActivity(intent);
    }

    public void create(View view) {
        Intent intent = new Intent(Splash_Screen.this, Register.class);
        startActivity(intent);
    }
}