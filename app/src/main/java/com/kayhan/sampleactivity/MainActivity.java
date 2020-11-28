package com.kayhan.sampleactivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kayhan.real_ui.handlers.LoginHandler;
import com.kayhan.real_ui.interfaces.LoginListener;
import com.kayhan.real_ui.logs.L;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    public static final int LOGIN_UI=122;
    public static final int SEARCH_ITEMS=211;

    public int currentTest = LOGIN_UI;


    @Click({R.id.loginBtn})
    public void onLoginButtonClicked(View v){
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            ((Button)v).setText("Login");
            FirebaseAuth.getInstance().signOut();
        }else{
            ((Button)v).setText("Log out");
            LoginHandler.getInstance()
                    .startLogin(v.getContext(), new LoginListener() {
                        @Override
                        public void onLoggedIn(FirebaseUser firebaseUser) {
                            if(firebaseUser!=null){
                                try {
                                    Toast.makeText(getBaseContext(),"Logged in: "+firebaseUser.getPhoneNumber(),Toast.LENGTH_LONG).show();
                                } catch (Exception e) {
                                    L.e(e);
                                }
                            }else{
                                Toast.makeText(getBaseContext(),"Login failed",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }

    @ViewById
    Button loginBtn;

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
        }else if(FirebaseAuth.getInstance().getCurrentUser()==null){
            loginBtn.setText("Login");
        }else{
            loginBtn.setText("Log out");
        }
    }
}