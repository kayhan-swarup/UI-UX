package com.kayhan.sampleactivity;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.kayhan.real_ui.ui.LoginActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    public static final int LOGIN_UI=122;
    public static final int SEARCH_ITEMS=211;

    public int currentTest = LOGIN_UI;

    @AfterViews
   public void init(){
        if (currentTest==SEARCH_ITEMS) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container,SearchFragment_.builder()
                            .collection("lab_tests")
                            .build())
                    .commit();
//            Intent intent = null;
//            try {
//                intent = new Intent(MainActivity.this, Class.forName("com.kayhan.sampleactivity.Login").getClass());
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//            startActivity(intent);
        }else{
            Intent intent = null;
            try {
                intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}